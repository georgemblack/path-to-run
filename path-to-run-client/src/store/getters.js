import isEmpty from 'lodash.isempty';

export default {
  startLocation: state => state.startLocation,
  startLocationExists: state => {
    return !isEmpty(state.startLocation);
  },
  routesRequestInProgress: state => state.routesRequestInProgress,
  routes: state => state.routes
}
