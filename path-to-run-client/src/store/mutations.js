const SET_START_LOCATION = 'SET_START_LOCATION';

export default {
  [SET_START_LOCATION](state, place) {
    state.startLocation = place;
  },
  setRoutesRequestInProgress (state, inProgress) {
    state.routesRequestInProgress = inProgress;
  },
  setRoutes (state, routes) {
    state.routes = routes
  }
}
