<template>
  <button :disabled="!enabled">
    <div v-show="!loading">
      Find Routes!
    </div>
    <div
      v-show="loading"
      class="load-animation"
    >
      <div></div>
    </div>
  </button>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'RouteFormSubmitButton',
  props: {
    loading: {
      type: Boolean,
      default: false
    },
    distance: {
      type: String,
      default: ''
    },
    shape: {
      type: String,
      default: ''
    },
  },
  computed: {
    enabled() {
      return (
        this.startLocationExists
        && this.distance !== ''
        && this.shape !== ''
        && !this.loading
      )
    },
    ...mapGetters(['startLocationExists'])
  }
}
</script>

<style lang="scss" scoped>
button {
  width: 10em;
  height: 2.5em;
}
@keyframes bouncing {
  to {
    opacity: 0.1;
    transform: translate3d(0, -10px, 0);
  }
}
.load-animation {
  transform: translateY(5px);
}
.load-animation > div, .load-animation:before, .load-animation:after {
  display: inline-block;
  width: 10px;
  height: 10px;
  background: #f7f7f7;
  border-radius: 50%;
  animation: bouncing 0.5s infinite alternate;
}
.load-animation > div, .load-animation:before, .load-animation:after {
  content: ' ';
}
.load-animation > div {
  margin: 0 5px;
}
.load-animation > div {
  animation-delay: 0.2s;
}
.load-animation:after {
  animation-delay: 0.4s;
}
</style>

