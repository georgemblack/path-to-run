<template>
  <div class="route-form">
    <form class="pure-form pure-form-stacked" @submit.prevent="submitForm">
      <input v-model.trim="startLat" type="text" placeholder="Latitude">
      <input v-model.trim="startLng" type="text" placeholder="Longitude">
      <input v-model.trim="distance" type="text" placeholder="Distance">
      <button class="button-primary pure-button">Submit</button>
    </form>
    <loading-spinner v-show="routesRequestInProgress"></loading-spinner>
  </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
import LoadingSpinner from './LoadingSpinner';

export default {
  name: 'RouteForm',
  components: { LoadingSpinner },
  data() {
    return {
      startLat: '',
      startLng: '',
      distance: '',
    }
  },
  methods: {
    submitForm() {
      this.getRoutes({
        startLat: this.startLat,
        startLng: this.startLng,
        distance: this.distance
      });
    },
    ...mapActions([
      'getRoutes'
    ])
  },
  computed: {
    ...mapGetters([
      'routesRequestInProgress'
    ])
  }
}
</script>

<style lang="scss" scoped>
.route-form {
  margin-top: 1em;
}
.button-primary {
  color: white;
  background: rgb(66, 184, 221);
  border-radius: 4px;
}
</style>
