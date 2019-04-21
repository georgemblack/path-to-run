const SET_START_LOCATION = 'SET_START_LOCATION'
const SET_ROUTES = 'SET_ROUTES'
const SET_ROUTES_REQUEST_IN_PROGRESS = 'SET_ROUTES_REQUEST_IN_PROGRESS'

export default {
  [SET_START_LOCATION](state, place) {
    state.startLocation = place
  },
  [SET_ROUTES](state, routes) {
    state.routes = routes
  },
  [SET_ROUTES_REQUEST_IN_PROGRESS](state, inProgress) {
    state.routesRequestInProgress = inProgress
  }
}
