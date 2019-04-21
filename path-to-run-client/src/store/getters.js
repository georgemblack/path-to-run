import isEmpty from 'lodash.isempty';

export default {
  startLocation: state => state.startLocation,
  startLocationExists: state => {
    return !isEmpty(state.startLocation);
  },
  routes: state => state.routes
}
