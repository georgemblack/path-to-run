<template>
  <div>
    <h1>Path to Run</h1>
    <route-map></route-map>
    <route-form></route-form>
  </div>
</template>

<script>
import RouteForm from './components/RouteForm';
import RouteMap from './components/RouteMap';

export default {
  name: 'App',
  components: { RouteForm, RouteMap }
}
</script>

<!-- WARNING: Not scoped -->
<style lang="scss">
body {
  max-width: 52em;
  margin: 2em auto 2em auto;
}
</style>
