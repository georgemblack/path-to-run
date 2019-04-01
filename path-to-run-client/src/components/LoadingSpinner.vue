<template>
  <div class="loading-spinner"></div>
</template>

<script>
export default {
  name: 'LoadingSpinner',
}
</script>

<style lang="scss" scoped>
.loading-spinner {
	pointer-events: none;
	width: 2.5em;
	height: 2.5em;
	border: 0.4em solid transparent;
	border-color: #eee;
	border-top-color: #3E67EC;
	border-radius: 50%;
	animation: spin 1s linear infinite;
}
@keyframes spin {
	100% {
		transform: rotate(360deg);
	}
}
</style>
