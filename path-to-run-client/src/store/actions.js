const getBaseUrl = () => {
  const base = window.location.host;
  const scheme = (base.includes('localhost')) ? 'http' : 'https';
  return `${scheme}://${base}`;
}

const getQueryString = (params) => {
  return '?' + Object.keys(params).map(k => k + '=' +params[k]).join('&');
}

export default {
  setStartLocation: ({ commit }, place) => {
    commit('SET_START_LOCATION', place);
  },
  getRoutes: ({ commit }, params) => {
    commit('setRoutesRequestInProgress', true);

    const base = getBaseUrl();
    const qs = getQueryString(params);
    const url = `${base}/path-to-run/routes${qs}`;
    console.log(`Sending request to: ${url}`);

    fetch(url, {
      method: 'GET'
    })
    .then(response => response.json())
    .then(response => {
      commit('setRoutes', response.routes);
      commit('setRoutesRequestInProgress', false);
    })
    .catch(err => {
      console.log('There was an error');
      commit('setRoutesRequestInProgress', false);
    });
  }
}
