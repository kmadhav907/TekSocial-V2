<template>
  <div
    class="friends-container"
    :class="{ 'shift-right': isSidebarExpanded }"
    style="background-color:black; margin: -8px z-index:99"
  >
    <div class="friends-input">
      <input
        class="input-text"
        type="text"
        placeholder="Search friends"
      />&nbsp;&nbsp;<svg
        xmlns="http://www.w3.org/2000/svg"
        width="24"
        height="24"
        fill="white"
        class="bi bi-search"
        viewBox="0 0 16 16"
      >
        <path
          d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z"
        />
      </svg>
    </div>
    <div class="friends-content">
      <div class="friends-left-content">
        <div class="friends-left-title">Friends you would like to connect</div>
        <div class="friends-grid" >
          <div class="card" v-for="(profile , index) in userProfiles" :key="index">
            <img
              src="https://images.unsplash.com/photo-1612428978260-2b9c7df20150?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=580&q=80"
              alt="Image"
            />
            <div class="details">
              <h2>{{ profile.profileName }}</h2>
              <button class="connect-btn" @click="connectTofriend(profile.profileId)">Connect</button>
            </div>
          </div>
        </div>
      </div>
      <div class="friends-right-content">
        <div class="friends-left-title">Accept request</div>
        <div class="request-lists">
          <div class="request-list-card" v-for="(request, index) in friendRequest" :key="index">
            <div class="request-list-card-title">{{ request.profileName }}</div>
            <button class="connect-btn request-btn" @click="acceptRequest(request.profileId)">Accept</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
  import {ref, onMounted} from "vue";
  import {getSearchedProfiles, getAllFriendRequests, sendFriendRequest, acceptFriendRequest} from "../services/APIServices"

  let userProfiles = ref([]);
  let friendRequest = ref([]);
  let userId = ref(2);
  onMounted(()=> {
    onLoadFunction();
  })

  const onLoadFunction = async()=> {
    getSearchedProfiles(null).then((response)=> {
      userProfiles.value = response.profileList.filter(profile => profile.profileId !== userId.value);
      console.log(response.profileList);
    }).catch((error)=> {
    });
    getAllFriendRequests(userId.value).then((response)=> {
      console.log(response.profileList);
      friendRequest.value = response.profileList;
    }).catch((error)=>{
      
    })
  }
  const connectTofriend = (profileId)=> {
    sendFriendRequest(profileId, userId.value).then((response)=> {
      console.log(response);
    }).catch((error)=> {
      console.log(error);
    })

  }
  const acceptRequest =(profileId)=> {
    acceptFriendRequest(profileId, userId.value).then((response)=> {
      console.log(response);
      getAllFriendRequests(userId.value).then((response)=> {
      console.log(response.profileList);
      friendRequest.value = response.profileList;
    }).catch((error)=>{
      
    })
    }).catch((error)=> {

    })

  }

</script>
<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
.friends-container {
  width: calc(100vw - 100px);
  height: 100%;
  position: fixed;
  left: 100px;
  padding: 0 10px;
  top: 0;
  display: flex;
  align-items: center;
  flex-direction: column;
  padding-top: 20px;
}
.friends-content {
  display: flex;
  gap: 25px;
  width: 100%;
  background-color: rgb(57, 57, 57);
  margin-top: 25px;
  border-radius: 5px;
  min-height: 100vh;
  justify-content: space-between;
}
.friends-left-content {
  display: flex;
  flex-direction: column;
}
.friends-grid {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  gap: 10px;
  padding: 10px;
}
.friends-left-title {
  font-size: 18px;
  line-height: 22px;
  color: #ffffff;
  font-family: "Open Sans";
  padding: 15px;
  text-align: left;;
  width: 100%;
}
.friends-right-content {
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-self: flex-end;
}
.friends-container-title {
  font-size: 22px;
  line-height: 25px;
  margin-top: 10px;
  color: black;
  text-align: center;
}
.right-title {
  font-family: "Open Sans";
  font-size: 18px;
  font-weight: 500;
  line-height: 24px;
  color: #ffffff;
  text-align: center;
  padding: 5px 0;
}
.request-container {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  padding: 10px;
}
.request-card {
  width: 100%;
  display: flex;
  border: 0.5px solid rgb(255, 255, 255);
  display: flex;
  justify-content: space-between;
  border-radius: 4px;
  align-items: center;
  padding: 6px 5%;
}
.request-name {
  font-family: "Open Sans";
  font-size: 18px;
  font-weight: 500;
  line-height: 24px;
  color: #ffffff;
  text-align: center;
}

.request-actions {
  display: flex;
  align-items: center;
}
.request-actions svg {
  color: #ffffff;
}
.card {
  width: 160px;
  aspect-ratio: 1/1;
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
  border-radius: 10px;
  overflow: hidden;
  box-shadow: rgba(50, 50, 93, 0.25) 0px 6px 12px -2px,
    rgba(0, 0, 0, 0.3) 0px 3px 7px -3px;
}

.card img {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  margin: 5px;
}

.card .details {
  margin-top: 10px;
}

.card .details h2 {
  font-weight: 600;
  font-size: 14px;
}

.card .details p {
  text-transform: uppercase;
  font-weight: 300;
}
.connect-btn {
  background-color: #d5af2f;
  border: none;
  padding: 4px 10px;
  border-radius: 4px;
  margin-top: 2px;
  box-shadow: 0 4px 6px rgba(50, 50, 93, 0.11), 0 1px 3px rgba(0, 0, 0, 0.08);
}
.request-lists {
  display: flex;
  align-items: center;
  justify-content: flex-start;
  flex-direction: column;
  width: 400px;
  padding: 10px 0;
}
.request-list-card {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 2%;
  background-color: #ffffff;
  -webkit-box-shadow: 10px 10px 24px -17px rgba(255, 255, 255, 0.75);
  -moz-box-shadow: 10px 10px 24px -17px rgba(255, 255, 255, 0.75);
  box-shadow: 10px 10px 24px -17px rgba(255, 255, 255, 0.75);
  width: 95%;
  border-radius: 5px;
}
.friends-input {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  padding: 20px;
  align-self: flex-start;
}
.friends-input > input {
  padding: 7px;
  border-radius: 6px;
  font-size: 16px;
  background: #fbfbfb;
  border: 2px solid transparent;
  height: 36px;
  border: none;
  outline: none;
  box-shadow: 0 0 0 1px #dddddd, 0 2px 4px 0 rgb(0 0 0 / 7%),
    0 1px 1.5px 0 rgb(0 0 0 / 5%);
}
.friends-input > input:focus {
  border: 0.1px solid #ffffff;
  border-radius: 4px;
  outline: none;
}
</style>
