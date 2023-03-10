<template>
    <div class="bg-center" ref="el">
        <div class="card" @click="goToProfile(profileId)">
            Name: Richa
        </div>
    </div>
</template>

<script setup>

import {ref, onMounted} from "vue"
const el = ref()

onMounted(() => {
  el.value // <div>
})
let profileId = ref(1);
const goToProfile = (id)=> {
    window.open("/profile/"+id);
}

</script>

<style scoped>
    .bg-center {
        width: calc(100vw - 130px);
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .card {
        width: 150px;
        height: 100px;
        background: yellow;
        font-family: 'Courier New', Courier, monospace;
        display: flex;;
        align-items: center;
        justify-content: center;
        border-radius: 10px;
        box-shadow: 10px 10px 10px rgba(0, 0, 0, 0.2);
    }
</style>

<script>
export default {
    name: "HomeView"
}
</script>