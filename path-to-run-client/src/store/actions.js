const getBase = () => {
  const base = window.location.host;
  const scheme = (base.includes('localhost')) ? 'http' : 'https';
  return `${scheme}://${base}`;
}

const getQueryString = (params) => {
  return '?' + Object.keys(params).map(k => k + '=' +params[k]).join('&');
}

export default {
  getRoutes: ({ commit }, params) => {
    commit('setRoutesRequestInProgress', true);

    const base = getBase();
    const qs = getQueryString(params);
    const url = `${base}/path-to-run/routes${qs}`;
    console.log(`Sending request to: ${url}`);

    fetch(url, {
      method: 'GET'
    })
    .then(response => {
      console.log(response);
      commit('setRoutesRequestInProgress', false);
    });
  }
}
