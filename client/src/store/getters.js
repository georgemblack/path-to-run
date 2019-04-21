import isEmpty from 'lodash.isempty'

export default {
  startLocation: state => state.startLocation,
  startLocationLat: state => {
    if (isEmpty(state.startLocation)) return ''
    return state.startLocation.geometry.location.lat().toString()
  },
  startLocationLng: state => {
    if (isEmpty(state.startLocation)) return ''
    return state.startLocation.geometry.location.lng().toString()
  },
  startLocationExists: state => {
    return !isEmpty(state.startLocation)
  },
  routes: state => state.routes,
  routesRequestInProgress: state => state.routesRequestInProgress
}
