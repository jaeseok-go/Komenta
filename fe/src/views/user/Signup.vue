<template>
	<div>
		<div class="temp-bc-title">
				<router-link to="/member/login">
					<img src="@/assets/images/KOMENTA_logo.png" alt="">
				</router-link>
				<hr>
		</div>
		<SignupForm></SignupForm>
	</div>
</template>

<script>
import SignupForm from '@/components/SignupForm.vue';

export default {
	components: {
		SignupForm,
	},
};
</script>
