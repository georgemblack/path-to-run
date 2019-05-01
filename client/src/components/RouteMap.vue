<template>
  <div
    ref="map"
    class="route-map"
  ></div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'RouteMap',
  data() {
    return {
      polylines: [],
    }
  },
  computed: {
    ...mapGetters([
      'routes'
    ])
  },
  watch: {
    /**
     * When routes are updated in store, update map
     * Remove existing polylines, generate new ones
     */
    routes(newRoutes) {
      this.clearPolylines()
      newRoutes.forEach(route => {
        /* eslint-disable-next-line no-undef */
        let newPolyline = new google.maps.Polyline({
          path: route.coordinates,
          strokeWeight: 5
        })
        this.addPolyline(newPolyline)
      })
    }
  },
  /**
   * Init Google Maps
   */
  mounted() {
    /* eslint-disable-next-line no-undef */
    this.map = new google.maps.Map(this.$refs.map, {
      center: {lat: 37.1, lng: -95.7},
      zoom: 4,
      clickableIcons: false,
      streetViewControl: false
    })
  },
  methods: {
    addPolyline(polyline) {
      polyline.setMap(this.map)
      this.polylines.push(polyline)
    },
    clearPolylines() {
      this.polylines.forEach(polyline => {
        polyline.setMap(null)
      })
      this.polylines = []
    }
  }
}
</script>

<style lang="scss" scoped>
.route-map {
  flex-grow: 1;
  height: 600px;
}
</style>
