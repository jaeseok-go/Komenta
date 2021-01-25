<template>
	<div>
		<div>
				<h1 class="title-logo">Komenta</h1>
				<hr>
		</div>
		<SignupForm></SignupForm>
	</div>
</template>

<script>
// import '@/assets/css/app.css';
import SignupForm from '@/components/SignupForm.vue';

export default {
	components: {
		SignupForm,
	},
};
</script>
