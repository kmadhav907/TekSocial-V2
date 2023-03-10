<template>
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">


  <div class="main-content" :class="{ 'shift-right': isSidebarExpanded }"
    style="background-color:black; margin: -8px z-index:99">

    <!-- Page content -->
    <div class="container-fluid">
      <div class="row" style="margin-left: 100px">
        <div class="col-xl-4 order-xl-2 mt-4 mb-5 mb-xl-0">
          <div class="card card-profile shadow" style="background-color:#393939">
            <div class="row justify-content-center">
              <div class="col-lg-3 order-lg-2">
                <div class="card-profile-image">
                  <div class="card-profile-image-wrapper">
                  <img :src="profilePic" class="rounded-circle">
                  <input id="fileUpload" type="file" @change="uploadFileToBackend" style="display: none;">
                  <svg
                  @click="uploadFile()"
                   xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="#d5af2f" class="bi bi-file-earmark-plus" viewBox="0 0 16 16">
  <path d="M8 6.5a.5.5 0 0 1 .5.5v1.5H10a.5.5 0 0 1 0 1H8.5V11a.5.5 0 0 1-1 0V9.5H6a.5.5 0 0 1 0-1h1.5V7a.5.5 0 0 1 .5-.5z"/>
  <path d="M14 4.5V14a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2h5.5L14 4.5zm-3 0A1.5 1.5 0 0 1 9.5 3V1H4a1 1 0 0 0-1 1v12a1 1 0 0 0 1 1h8a1 1 0 0 0 1-1V4.5h-2z"/>
</svg>
                  </div>
                </div>
              </div>
            </div>
            <div class="card-header text-center border-0" style="background-color: #1C1C1C">
              <div class="col-12 col-md-4 text-md-right">
                <button class="btn btn-info float-md-right w-100 mb-2 mb-md-0">Chat</button>
              </div>
            </div>
            <div class="card-body pt-0 pt-md-4 ">
              <div class="row">
                <div class="col">
                  <div class="card-profile-stats d-flex justify-content-center mt-md-5">
                    <div>

                      <span class="description">Username : {{ userName }}</span>
                    </div>

                  </div>
                </div>
              </div>
              <div class="text-center">
                <h3>
                  {{ firstName }} {{ lastName }} , <span class="font-weight-light"> {{ age }}</span>
                </h3>

                <div class="h5 font-weight-300">
                  <i class="ni location_pin mr-2"></i>{{ city }}, {{ state }}
                </div>
                <div class="h5 mt-4">
                  <i class="ni business_briefcase-24 mr-2"></i>{{ userEmail }}
                </div>
                <div>
                  <i class="ni education_hat mr-2"></i>{{ userContact }}
                </div>
                <hr class="my-4">
                <p>{{ userAbout }}</p>

              </div>
            </div>
          </div>
        </div>
        <div class="col-xl-8 order-xl-1 mt-4">
          <div class="card bg-secondary shadow">
            <div class="card-header border-0" style="background-color:#393939">
              <div class="row align-items-center">
                <div class="col-8">
                  <h3 class="mb-0">My account</h3>
                </div>
                <div class="col-4 text-right">
                  <a href="#!" class="btn btn-info float-right">Settings</a>
                  <button v-if="!editMode" class="btn btn-info float-right" @click="editMode = true">Edit</button>

                </div>
              </div>
            </div>
            <div class="card-body">
              <form @submit.prevent="submitDataToBacked">
                <h6 class="heading-small text-muted mb-4">User information</h6>
                <div class="pl-lg-4">
                  <div class="row">
                    <div class="col-lg-6">
                      <div class="form-group focused">
                        <label class="form-control-label" for="input-username">Username</label>
                        <input type="text" id="input-username" class="form-control form-control-alternative"
                          placeholder="Username" v-model.trim="userName" :readonly="!editMode" :required="editMode"
                          :class="{ 'is-invalid': userNameError }">
                        <div class="invalid-feedback">{{ userNameError }}</div>
                      </div>
                    </div>
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-email">Email address</label>
                        <input type="email" id="input-email" class="form-control form-control-alternative"
                          placeholder="jesse@example.com" v-model.trim="userEmail" :readonly="!editMode"
                          :required="editMode" :class="{ 'is-invalid': userEmailError }">
                        <div class="invalid-feedback">{{ userEmailError }}</div>
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-lg-6">
                      <div class="form-group focused">
                        <label class="form-control-label" for="input-first-name">First name</label>
                        <input type="text" id="input-first-name" class="form-control form-control-alternative"
                          placeholder="First name" v-model="firstName" :readonly="!editMode" :required="editMode"
                          :class="{ 'is-invalid': userfirstNameError }">
                        <div class="invalid-feedback">{{ userfirstNameError }}</div>
                      </div>
                    </div>
                    <div class="col-lg-6">
                      <div class="form-group focused">
                        <label class="form-control-label" for="input-last-name">Last name</label>
                        <input type="text" id="input-last-name" class="form-control form-control-alternative"
                          placeholder="Last name" v-model="lastName" :readonly="!editMode" :required="editMode"
                          :class="{ 'is-invalid': userlastNameError }">
                        <div class="invalid-feedback">{{ userlastNameError }}</div>
                      </div>
                    </div>
                  </div>
                </div>
                <hr class="my-4">

                <!--Address-->

                <h6 class="heading-small text-muted mb-4">Other Information</h6>
                <div class="pl-lg-4">
                  <div class="row">
                    <div class="col-lg-6">
                      <div class="form-group focused">
                        <label class="form-control-label" for="input-username">Age</label>
                        <input type="text" id="input-username" class="form-control form-control-alternative"
                          placeholder="Age" v-model="age" :readonly="!editMode">
                      </div>
                    </div>
                    <div class="col-lg-6">
                      <div class="form-group">
                        <label class="form-control-label" for="input-email">City</label>
                        <input type="text" id="input-email" class="form-control form-control-alternative"
                          placeholder="City" v-model="city" :readonly="!editMode">
                      </div>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-lg-6">
                      <div class="form-group focused">
                        <label class="form-control-label" for="input-first-name">State</label>
                        <input type="text" id="input-first-name" class="form-control form-control-alternative"
                          placeholder="state" v-model="state" :readonly="!editMode">
                      </div>
                    </div>

                  </div>
                </div>
                <hr class="my-4">

                <!-- Contact -->
                <h6 class="heading-small text-muted mb-4">Contact information</h6>
                <div class="pl-lg-4">
                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group focused">
                        <label class="form-control-label" form="input-contact">Contact</label>
                        <input id="input-address" class="form-control form-control-alternative" placeholder="Contact No."
                          type="text" v-model="userContact" :readonly="!editMode">
                      </div>
                    </div>
                  </div>

                </div>
                <hr class="my-4">
                <!-- Description -->
                <h6 class="heading-small text-muted mb-4">About me</h6>
                <div class="pl-lg-4">
                  <div class="form-group focused">
                    <label>About Me</label>
                    <textarea rows="4" class="form-control form-control-alternative"
                      placeholder="A few words about you ..." v-model="userAbout"
                      :readonly="!editMode">Passionate about Life</textarea>
                  </div>
                </div>

                <div class="submit-button">

                  <button v-if="editMode" class="btn mt-4"
                    :disabled="userNameError || userEmailError || userfirstNameError || userlastNameError"
                    @click="submitDataToBacked(); editMode = false; showModal()">Save</button>
                </div>
                <div class="modal fade" id="successModal" tabindex="-1" aria-labelledby="successModalLabel"
                  aria-hidden="true">
                  <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                    <div class="modal-content bg-dark text-light">
                      <div class="modal-header">
                        <h5 class="modal-title" id="successModalLabel">Success</h5>
                        <button type="button" class="btn-close text-light" data-bs-dismiss="modal"
                          aria-label="Close"></button>
                      </div>
                      <div class="modal-body">
                        Your changes have been saved successfully.
                      </div>
                    </div>
                  </div>
                </div>



              </form>

            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>




<script setup>
import { ref, onMounted, watch, computed } from "vue";
import { getDataForUser, updateDataForUser, uploadImageFile } from "../services/APIServices";

import { Modal } from 'bootstrap';


const userNameError = ref(null);
const userEmailError = ref(null);
const userfirstNameError = ref(null);
const userlastNameError = ref(null);
const editMode = ref(false);



let firstName = ref(null);
let lastName = ref(null);
let userName = ref(null);
let userEmail = ref(null);
let profilePic = ref(null);
let userContact = ref("");
let userAbout = ref("");
let age = ref("");
let city = ref("");
let state = ref("");
let idOfUser = window.location.href.split("/").pop();
onMounted(() => getDataFromBackend(idOfUser));

const uploadFile = ()=> {
  document.getElementById("fileUpload").click();
}
// let showSuccessAlert =false ;
const uploadFileToBackend = (event)=> {
  let image = event.target.files[0];
  let reader = new FileReader();

  reader.onload = event => {
    profilePic.value = event.target.result;
  }
  reader.readAsDataURL(image);
  let formData = new FormData();
  formData.append("file", image);
  formData.append("userId", idOfUser);
  uploadImageFile(formData).then((response)=> {
    profilePic.value = response.data
  }).catch((error)=> {
    console.log(error);
  })

}
watch(userName, (val) => {
  if (!val && editMode.value) {
    userNameError.value = 'Username is required';
  } else {
    userNameError.value = null;
  }
});

watch(firstName, (val) => {
  if (!val && editMode.value) {
    userfirstNameError.value = 'First Name is required';
  } else {
    userfirstNameError.value = null;
  }
});

watch(lastName, (val) => {
  if (!val && editMode.value) {
    userlastNameError.value = 'Last Name is required';
  } else {
    userlastNameError.value = null;
  }
});

watch(userEmail, (val) => {
  if (!val && editMode.value) {
    userEmailError.value = 'Email is required';
  } else {
    userEmailError.value = null;
  }
});

const hasErrors = computed(() => {
  return !!userNameError.value || !!userEmailError.value ||
    !!userfirstNameError.value || !!userlastNameError.value;
});

const getDataFromBackend = (id) => {
  getDataForUser(id)
    .then((response) => {
      console.log(response);
      firstName.value = response.firstName;
      lastName.value = response.lastName;
      userName.value = response.userName;
      userEmail.value = response.userEmail;
      userContact.value = response.userContact;
      userAbout.value = response.userAbout;
      city.value = response.city;
      age.value = response.age;
      state.value = response.state;
      profilePic.value = response.profilePic || "https://picsum.photos/200";
    })
    .catch((response) => {
      console.log(response);
      // window.alert("No user is found");
      window.close();
    });
};

const submitDataToBacked = () => {
  // if(!checkEmail(userEmail.value)){
  //   window.alert("Please enter proper email address")
  //   return;
  // }
  // if(!checkPhoneNumber(userContact.value)){
  //   window.alert("Please enter proper contact");
  //   return;
  // }
  // Validation is required for the same;
  updateDataForUser(
    idOfUser,
    userName.value,
    userEmail.value,
    firstName.value,
    lastName.value,
    userContact.value,
    userAbout.value,
    age.value,
    city.value,
    state.value
  )
    .then((response) => {
      console.log(response);
      // window.alert("Profile Updated Successfully");
      this.showSuccessAlert = true;
      setTimeout(() => {
        this.showSuccessAlert = false;
      }, 3000);
      // isSubmitted = true;
    })
    .catch((response) => {
      // window.alert("Something went wrong");
      console.log(response);
    });
};

function showModal() {
  var successModal = new Modal(document.getElementById('successModal'), {
    backdrop: 'static',
    keyboard: false
  });
  successModal.show();
}

</script>



<script>

// import SideNav from './components/SideNav.vue'

// import ModalConfirm from "./globalComponents/ModalConfirm.vue";




export default {
  name: "ProfileDetailsView",
  // components: {
  //   ModalConfirm
  // },
  props: {
    // isSidebarExpanded: Boolean,

  },
  data() {
    return {
      //  showSuccessAlert: false,
      editMode: false,
    }

  },
  // methods: {
  //   showModal() {
  //     var successModal = new bootstrap.Modal(document.getElementById('successModal'), {
  //       backdrop: 'static',
  //       keyboard: false
  //     });
  //     successModal.show();
  //   }
  // }

};
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
:root {
  --light: #adb5bd;
  --dark: #212529;
  --default: #172b4d;
  --white: #fff;
  --neutral: #fff;
  --darker: black;
  --breakpoint-xs: 0;
  --breakpoint-sm: 576px;
  --breakpoint-md: 768px;
  --breakpoint-lg: 992px;
  --breakpoint-xl: 1200px;
}

*,
*::before,
*::after {
  box-sizing: border-box;
}

* {
  font-family: "Open Sans";
  font-size: large;
  color: white;
}

.alert-success {
  color: #155724;
  background-color: #d4edda;
  border-color: #c3e6cb;
  padding: 0.75rem 1.25rem;
  border-radius: 0.25rem;
  justify-content: center;
}

@-ms-viewport {
  width: device-width;
}

figcaption,
footer,
header,
main,
nav,
section {
  display: block;
}

[tabindex="-1"]:focus {
  outline: 0 !important;
}

hr {
  overflow: visible;
  box-sizing: content-box;
  height: 0;
}

h1,
h3,
h4,
h5,
h6 {
  margin-top: 0;
  margin-bottom: 0.5rem;
}

p {
  margin-top: 0;
  margin-bottom: 1rem;
}

strong {
  font-weight: bolder;
}

a {
  text-decoration: none;
  color: white;
  background-color: transparent;
  -webkit-text-decoration-skip: objects;
}

a:not([href]):not([tabindex]):hover,
a:not([href]):not([tabindex]):focus {
  text-decoration: none;
  color: inherit;
}

a:not([href]):not([tabindex]):focus {
  outline: 0;
}

label {
  display: inline-block;
  margin-bottom: 0.5rem;
}

button {
  border-radius: 0;
}

button:focus {
  outline: 1px dotted;
  outline: 5px auto -webkit-focus-ring-color;
}

input,
button,
textarea {
  font-family: inherit;
  font-size: inherit;
  line-height: inherit;
  margin: 0;
}

button,
input {
  overflow: visible;
}

button {
  text-transform: none;
}

button,
html [type="button"],
[type="reset"],
[type="submit"] {
  -webkit-appearance: button;
}

button::-moz-focus-inner,
[type="button"]::-moz-focus-inner,
[type="reset"]::-moz-focus-inner,
[type="submit"]::-moz-focus-inner {
  padding: 0;
  border-style: none;
}

input[type="radio"],
input[type="checkbox"] {
  box-sizing: border-box;
  padding: 0;
}

input[type="date"],
input[type="time"],
input[type="datetime-local"],
input[type="month"] {
  -webkit-appearance: listbox;
}

textarea {
  overflow: auto;
  resize: vertical;
}

legend {
  font-size: 1.5rem;
  line-height: inherit;
  display: block;
  width: 100%;
  max-width: 100%;
  margin-bottom: 0.5rem;
  padding: 0;
  white-space: normal;
  color: inherit;
}

[type="number"]::-webkit-inner-spin-button,
[type="number"]::-webkit-outer-spin-button {
  height: auto;
}

[type="search"] {
  outline-offset: -2px;
  -webkit-appearance: none;
}

[type="search"]::-webkit-search-cancel-button,
[type="search"]::-webkit-search-decoration {
  -webkit-appearance: none;
}

::-webkit-file-upload-button {
  font: inherit;
  -webkit-appearance: button;
}

[hidden] {
  display: none !important;
}

h5,
.h5 {
  font-size: 1rem;
}

hr {
  margin-top: 2rem;
  margin-bottom: 2rem;
  border: 0;
  border-top: 1px solid rgba(0, 0, 0, 0.1);
}

code {
  font-size: 87.5%;
  word-break: break-word;
  color: white;
}

a>code {
  color: inherit;
}

.container-fluid {
  width: 100%;
  margin-right: auto;
  margin-left: auto;
  padding-right: 15px;
  padding-left: 15px;
}

.row {
  display: flex;
  margin-right: -15px;
  margin-left: -15px;
  flex-wrap: wrap;
}

.col-4,
.col-8,
.col,
.col-md-10,
.col-md-12,
.col-lg-3,
.col-lg-4,
.col-lg-6,
.col-lg-7,
.col-xl-4,
.col-xl-6,
.col-xl-8 {
  position: relative;
  width: 100%;
  min-height: 1px;
  padding-right: 15px;
  padding-left: 15px;
}

.col {
  max-width: 100%;
  flex-basis: 0;
  flex-grow: 1;
}

.col-4 {
  max-width: 33.33333%;
  flex: 0 0 33.33333%;
}

.col-8 {
  max-width: 66.66667%;
  flex: 0 0 66.66667%;
}

@media (min-width: 768px) {
  .col-md-12 {
    max-width: 100%;
    flex: 0 0 100%;
  }
}

@media (min-width: 992px) {
  .col-lg-3 {
    max-width: 25%;
    flex: 0 0 25%;
  }

  .col-lg-6 {
    max-width: 50%;
    flex: 0 0 50%;
  }

  .order-lg-2 {
    order: 2;
  }
}

@media (min-width: 1200px) {
  .col-xl-4 {
    max-width: 33.33333%;
    flex: 0 0 33.33333%;
  }

  .col-xl-6 {
    max-width: 50%;
    flex: 0 0 50%;
  }

  .col-xl-8 {
    max-width: 66.66667%;
    flex: 0 0 66.66667%;
  }

  .order-xl-1 {
    order: 1;
  }

  .order-xl-2 {
    order: 2;
  }
}

.form-control {
  padding-left: 12px;
  font-size: 2em;
  line-height: 1.5;
  display: block;
  width: 100%;
  height: calc(2.75rem + 2px);
  padding: 0.625rem 0.75rem;
  transition: all 0.2s cubic-bezier(0.68, -0.55, 0.265, 1.55);
  color: white;
  border: 1px solid white;
  border-radius: 0.375rem;
  background-color: dimgray;
  background-clip: padding-box;
  box-shadow: none;
}

.form-control[data-v-469af010]:not(:first-child) {
  padding-left: 12px;
  border-left: 0;
}

@media screen and (prefers-reduced-motion: reduce) {
  .form-control {
    transition: none;
  }
}

.form-control::-ms-expand {
  border: 0;
  background-color: transparent;
}

.form-control:focus {
  color: black;
  text-decoration-line: var(--darker);
  border-color: rgba(50, 151, 211, 0.25);
  outline: 0;
  background-color: white;
  box-shadow: none, none;
}

.form-control:-ms-input-placeholder {
  opacity: 1;
  color: #adb5bd;
}

.form-control::-ms-input-placeholder {
  opacity: 1;
  color: #adb5bd;
}

.form-control::placeholder {
  opacity: 1;
  color: #adb5bd;
}

.form-control:disabled,
.form-control[readonly] {
  opacity: 1;
  background-color: dimgray;
}

textarea.form-control {
  height: auto;
}

.form-group {
  margin-bottom: 1.5rem;
}

@media (min-width: 576px) {
  .form-inline .form-group {
    display: flex;
    margin-bottom: 0;
    flex: 0 0 auto;
    flex-flow: row wrap;
    align-items: center;
  }
}

.btn {
  font-size: 1rem;
  font-weight: 600;
  line-height: 1.5;
  display: inline-block;
  padding: 0.625rem 1.25rem;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  transition: color 0.15s ease-in-out, background-color 0.15s ease-in-out,
    border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
  text-align: center;
  vertical-align: middle;
  white-space: nowrap;
  border: 1px solid transparent;
  border-radius: 0.375rem;
}

@media screen and (prefers-reduced-motion: reduce) {
  .btn {
    transition: none;
  }
}

.btn:hover,
.btn:focus {
  text-decoration: none;
}

.btn:focus {
  outline: 0;
  box-shadow: 0 7px 14px rgba(50, 50, 93, 0.1), 0 3px 6px rgba(0, 0, 0, 0.08);
}

.btn:disabled {
  opacity: 0.65;
  box-shadow: none;
}

.btn:not(:disabled):not(.disabled) {
  cursor: pointer;
}

.btn:not(:disabled):not(.disabled):active {
  box-shadow: none;
}

.btn:not(:disabled):not(.disabled):active:focus {
  box-shadow: 0 7px 14px rgba(50, 50, 93, 0.1), 0 3px 6px rgba(0, 0, 0, 0.08),
    none;
}

.btn-info {
  color: white;
  border-color: white;
  background-color: #1c1c1c;
  box-shadow: 0 4px 6px rgba(50, 50, 93, 0.11), 0 1px 3px rgba(0, 0, 0, 0.08);
}

.btn-info:hover {
  color: white;
  border-color: white;
  background-color: #1c1c1c;
}

.btn-info:focus {
  box-shadow: 0 4px 6px rgba(50, 50, 93, 0.11), 0 1px 3px rgba(0, 0, 0, 0.08),
    0 0 0 0 rgba(17, 205, 239, 0.5);
}

.btn-info:disabled {
  color: white;
  border-color: white;
  background-color: #1c1c1c;
}

.btn-info:not(:disabled):not(.disabled):active {
  color: white;
  border-color: white;
  background-color: #1c1c1c;
}

.btn-info:not(:disabled):not(.disabled):active:focus {
  box-shadow: none, 0 0 0 0 rgba(17, 205, 239, 0.5);
}

.btn-default {
  color: white;
  border-color: white;
  background-color: #1c1c1c;
  box-shadow: 0 4px 6px rgba(50, 50, 93, 0.11), 0 1px 3px rgba(0, 0, 0, 0.08);
}

.btn-default:hover {
  color: white;
  border-color: white;
  background-color: #1c1c1c;
}

.btn-default:focus {
  box-shadow: 0 4px 6px rgba(50, 50, 93, 0.11), 0 1px 3px rgba(0, 0, 0, 0.08),
    0 0 0 0 rgba(23, 43, 77, 0.5);
}

.btn-default:disabled {
  color: white;
  border-color: white;
  background-color: #1c1c1c;
}

.btn-default:not(:disabled):not(.disabled):active {
  color: white;
  border-color: white;
  background-color: #1c1c1c;
}

.btn-default:not(:disabled):not(.disabled):active:focus {
  box-shadow: none, 0 0 0 0 rgba(23, 43, 77, 0.5);
}

.btn-sm {
  font-size: 0.875rem;
  line-height: 1.5;
  padding: 0.25rem 0.5rem;
  border-radius: 0.375rem;
}

.navbar>.container,
.navbar>.container-fluid {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: space-between;
}

@media (max-width: 767.98px) {

  .navbar-expand-md>.container,
  .navbar-expand-md>.container-fluid {
    padding-right: 0;
    padding-left: 0;
  }
}

.navbar-dark .navbar-nav .nav-link {
  color: rgba(255, 255, 255, 0.95);
}

.card {
  position: relative;
  display: flex;
  flex-direction: column;
  min-width: 0;
  word-wrap: break-word;
  border: 1px solid rgba(0, 0, 0, 0.05);
  border-radius: 0.375rem;
  background-color: #fff;
  background-clip: border-box;
}

.card>hr {
  margin-right: 0;
  margin-left: 0;
}

.card-body {
  padding: 1.5rem;
  flex: 1 1 auto;
}

.card-header {
  margin-bottom: 0;
  padding: 1.25rem 1.5rem;
  border-bottom: 1px solid rgba(0, 0, 0, 0.05);
  background-color: #fff;
}

.card-header:first-child {
  border-radius: calc(0.375rem - 1px) calc(0.375rem - 1px) 0 0;
}

.bg-secondary {
  background-color: #1c1c1c !important;
}

a.bg-secondary:hover,
a.bg-secondary:focus,
button.bg-secondary:hover,
button.bg-secondary:focus {
  background-color: #1c1c1c !important;
}

.border-0 {
  border: 0 !important;
}

.rounded-circle {
  border-radius: 50% !important;
}

.d-flex {
  display: flex !important;
}

.justify-content-center {
  justify-content: center !important;
}

.justify-content-between {
  justify-content: space-between !important;
}

.align-items-center {
  align-items: center !important;
}

@media (min-width: 1200px) {
  .justify-content-xl-between {
    justify-content: space-between !important;
  }
}

.float-right {
  float: right !important;
}

.shadow,
.card-profile-image img {
  box-shadow: 0 0 2rem 0 rgba(136, 152, 170, 0.15) !important;
}

.mb-0 {
  margin-bottom: 0 !important;
}

.mr-2 {
  margin-right: 0.5rem !important;
}

.mt-4,
.my-4 {
  margin-top: 1.5rem !important;
}

.mr-4 {
  margin-right: 1.5rem !important;
}

.mb-4,
.my-4 {
  margin-bottom: 1.5rem !important;
}

.mb-5 {
  margin-bottom: 3rem !important;
}

.pt-0 {
  padding-top: 0 !important;
}

.pr-0 {
  padding-right: 0 !important;
}

.pb-0 {
  padding-bottom: 0 !important;
}

.pt-5 {
  padding-top: 3rem !important;
}

.pt-8 {
  padding-top: 8rem !important;
}

@media (min-width: 768px) {
  .mt-md-5 {
    margin-top: 3rem !important;
  }

  .pt-md-4 {
    padding-top: 1.5rem !important;
  }

  .pb-md-4 {
    padding-bottom: 1.5rem !important;
  }
}

@media (min-width: 992px) {
  .pl-lg-4 {
    padding-left: 1.5rem !important;
  }

  .pt-lg-8 {
    padding-top: 8rem !important;
  }

  .ml-lg-auto {
    margin-left: auto !important;
  }
}

@media (min-width: 1200px) {
  .mb-xl-0 {
    margin-bottom: 0 !important;
  }
}

.text-right {
  text-align: right !important;
}

.text-center {
  text-align: center !important;
}

.font-weight-light {
  font-weight: 300 !important;
}

.text-muted {
  color: #8898aa !important;
}

[class*="shadow"] {
  transition: all 0.15s ease;
}

.font-weight-300 {
  font-weight: 300 !important;
}

.btn {
  font-size: 0.875rem;
  position: relative;
  transition: all 0.15s ease;
  letter-spacing: 0.025em;
  text-transform: none;
  will-change: transform;
}

.btn:hover {
  transform: translateY(-1px);
  box-shadow: 0 7px 14px rgba(50, 50, 93, 0.1), 0 3px 6px rgba(0, 0, 0, 0.08);
}

.btn:not(:last-child) {
  margin-right: 0.5rem;
  margin-left: 0.5rem;
}

.btn i:not(:first-child) {
  margin-left: 0.5rem;
}

.btn i:not(:last-child) {
  margin-right: 0.5rem;
}

.input-group .btn {
  margin-right: 0;
  transform: translateY(0);
}

.btn-sm {
  font-size: 0.75rem;
}

[class*="btn-outline-"] {
  border-width: 1px;
}

.card-profile-image {
  position: relative;
}

.card-profile-image .card-profile-image-wrapper {
  margin-top: 93px;
  position: absolute;
  left: 50%;
  z-index: 10;
}
.card-profile-image img {
  height: 150px;
  width: 150px;
  position: relative;
  transition: all 0.15s ease;
  transform: translate(-50%, -30%);
  border-radius: 50%;
}
.card-profile-image svg, .card-profile-image input {
  position: absolute;
  left: 45%;
  bottom: 52px;
}
.card-profile-image img:hover {
  transform: translate(-50%, -33%);
}

.card-profile-stats {
  padding: 1rem 0;
}

.card-profile-stats>div {
  margin-top: 70px;
  margin-right: 1rem;
  padding: 0.875rem;
  text-align: center;
}

.card-profile-stats>div:last-child {
  margin-right: 0;
}

.card-profile-stats>div .heading {
  font-size: 1.1rem;
  font-weight: bold;
  display: block;
}

.card-profile-stats>div .description {
  font-size: 0.875rem;
  color: #adb5bd;
}

.main-content .navbar-top {
  position: absolute;
  z-index: 1;
  top: 0;
  left: 0;
  width: 100%;
  padding-right: 0 !important;
  padding-left: 0 !important;
  background-color: transparent;
}

@media (min-width: 768px) {
  .main-content .container-fluid {
    padding-right: 39px !important;
    padding-left: 39px !important;
  }
}

.form-control-label {
  font-size: 1rem;
  font-weight: 600;
  color: white;
}

.form-control {
  font-size: 0.875rem;
}

.form-control:focus:-ms-input-placeholder {
  color: #adb5bd;
}

.form-control:focus::-ms-input-placeholder {
  color: #adb5bd;
}

.form-control:focus::placeholder {
  color: #adb5bd;
}

textarea[resize="none"] {
  resize: none !important;
}

textarea[resize="both"] {
  resize: both !important;
}

textarea[resize="vertical"] {
  resize: vertical !important;
}

textarea[resize="horizontal"] {
  resize: horizontal !important;
}

.form-control-alternative {
  transition: box-shadow 0.15s ease;
  border: 0;
  box-shadow: 0 1px 3px rgba(50, 50, 93, 0.15), 0 1px 0 rgba(0, 0, 0, 0.02);
}

.form-control-alternative:focus {
  box-shadow: 0 4px 6px rgba(50, 50, 93, 0.11), 0 1px 3px rgba(0, 0, 0, 0.08);
}

.focused .input-group-alternative {
  box-shadow: 0 4px 6px rgba(50, 50, 93, 0.11), 0 1px 3px rgba(0, 0, 0, 0.08) !important;
}

.focused .input-group {
  box-shadow: none;
}

.focused .input-group-text {
  color: #8898aa;
  border-color: rgba(50, 151, 211, 0.25);
  background-color: #fff;
}

.focused .form-control {
  border-color: rgba(50, 151, 211, 0.25);
}

.form-control {
  box-shadow: none;
}

.form-control:not(:first-child) {
  padding-left: 12px;
  border-left: 0;
}

.form-control:not(:last-child) {
  padding-right: 0;
  border-right: 0;
}

.focused {
  box-shadow: none;
}

.description {
  font-size: 0.875rem;
}

.heading {
  font-size: 0.95rem;
  font-weight: 600;
  letter-spacing: 0.025em;
  text-transform: uppercase;
}

.heading-small {
  font-size: 1rem;
  padding-top: 0.25rem;
  padding-bottom: 0.25rem;
  letter-spacing: 0.04em;
  text-transform: uppercase;
}

.btn[data-v-469af010]:not(:last-child) {
  margin-right: 0.5rem;
  margin-left: 1rem;
}

@media (max-width: 768px) {
  .btn {
    margin-bottom: 10px;
  }
}

.shift-right {
  margin-left: 250px;
  /* Change this value to match the width of your sidebar */
  transition: margin-left 0.3s ease;
}

.submit-button {
  display: flex;
  justify-content: center;

}

input.disabled-input {
  background-color: #f8f9fe;
  color: #8898aa;
  cursor: not-allowed;
}
</style>
