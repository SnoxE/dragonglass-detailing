import * as THREE from 'three'
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls'
import { GLTFLoader } from 'three/addons/loaders/GLTFLoader.js'

const scene = new THREE.Scene()

// const geometry = new THREE.SphereGeometry(2, 64, 64)
// const material = new THREE.MeshStandardMaterial({
//   color: '#ff6d01',
// })

// const mesh = new THREE.Mesh(geometry, material)
// scene.add(mesh)

const loader = new GLTFLoader()

loader.load(
  './porsche_911_gt2_rs/scene.gltf',
  function (gltf) {
    scene.add(gltf.scene)
  },
  undefined,
  function (error) {
    console.error(error)
  }
)

const dimensions = {
  width: window.innerWidth,
  height: window.innerHeight
}

const light = new THREE.PointLight(0xffffff, 200, 100)
light.position.set(0, 10, 10)
scene.add(light)

const camera = new THREE.PerspectiveCamera(45, dimensions.width / dimensions.height, 0.1, 100)
camera.position.z = 15
scene.add(camera)

const canvas = document.querySelector('.glcanvas')
const renderer = new THREE.WebGLRenderer({ canvas })
renderer.setSize(dimensions.width, dimensions.height)
renderer.setPixelRatio(2)
renderer.render(scene, camera)

const controls = new OrbitControls(camera, canvas)
controls.enableDamping = true
controls.autoRotate = true

window.addEventListener('resize', () => {
  dimensions.width = window.innerWidth
  dimensions.height = window.innerHeight

  camera.aspect = dimensions.width / dimensions.height
  camera.updateProjectionMatrix()
  renderer.setSize(dimensions.width, dimensions.height)
})

const loop = () => {
  controls.update()
  renderer.render(scene, camera)
  window.requestAnimationFrame(loop)
}

loop()
