<!-- <template>
  <div class="calendar">
    <div class="calendar-row calendar-header">
      <div v-for="day in days" :key="day" class="calendar-cell">{{ day }}</div>
    </div>

    <div v-for="time in times" :key="time" class="calendar-row">
      <div v-for="day in days" :key="day + time" class="calendar-cell"></div>
    </div>
  </div>
</template> -->

<template>
  <div class="calendar">
    <!-- Days Header -->
    <div class="calendar-row calendar-header">
      <div v-for="day in days" :key="day" class="calendar-cell">{{ day }}</div>
    </div>

    <!-- Time Slots -->
    <div v-for="(time, timeIndex) in times" :key="time" class="calendar-row">
      <div v-for="(day, dayIndex) in days" :key="day + time" class="calendar-cell">
        <div
          v-for="reservation in filterReservations(dayIndex, timeIndex)"
          :key="reservation"
          :style="{ height: reservation.height + 'px' }"
          class="reservation"
        >
          {{ reservation.title }}
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'

const days = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday']
const times = [
  '09:00',
  '10:00',
  '11:00',
  '12:00',
  '13:00',
  '14:00',
  '15:00',
  '16:00',
  '17:00',
  '18:00',
  '19:00'
]

// This would be fetched or derived from your reservations data
const reservations = ref([
  // Example reservation
  { day: 'Monday', startTime: '09:00', endTime: '11:00', title: 'Meeting' }
  // Add more reservations as needed
])
const timeSlotHeight = 20
const filterReservations = (dayIndex, timeIndex) => {
  return reservations.value
    .filter((reservation) => {
      const startIdx = times.indexOf(reservation.startTime)
      const endIdx = times.indexOf(reservation.endTime)
      return reservation.dayIndex === dayIndex && startIdx <= timeIndex && timeIndex < endIdx
    })
    .map((reservation) => {
      const span = times.indexOf(reservation.endTime) - times.indexOf(reservation.startTime)
      const height = span * timeSlotHeight
      return {
        ...reservation,
        height
      }
    })
}
</script>

<style>
.calendar {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  grid-gap: 5px;
}

.calendar-row {
  display: contents;
}

.calendar-cell {
  border: 1px solid #ddd;
  padding: 10px;
  position: relative; /* Needed for absolute positioning of reservations */
  min-height: 20px; /* Adjust as needed */
}

.calendar-header {
  font-weight: bold;
  background-color: #f3f3f3;
}

.reservation {
  position: absolute;
  width: 100%;
  background-color: #add8e6;
  z-index: 10;
  text-align: center;
  border-radius: 4px;
  padding: 2px;
}
</style>
