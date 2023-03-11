<template>

    <h1>Hello</h1>
    <input type="text" v-model="messageText"/>
    <button @click="sendMessage">Submit</button>
</template>

<script setup>
    import {ref, onMounted} from "vue"
    let userId = ref(1);
    let senderId = ref(2);
    let stompClient = null;
    let messages = ref([]);
    let messageText = ref("");

    onMounted(()=> {
        connectToSocket();
    })
    const connectToSocket = ()=> {
    const Stomp = require("stompjs");
    var SockJS = require("sockjs-client");
    SockJS = new SockJS("http://localhost:8083/ws");
    stompClient = Stomp.over(SockJS);
    stompClient.connect({}, onConnected, onError);
    console.log("Connected successfully")
    }

    const onConnected = () => {
    console.log("connected");
    console.log(userId);
    stompClient.subscribe(
      "/user/" + userId.value + "/queue/messages",
      onMessageReceived
    );
  };
  
  const onError = (err) => {
    console.log(err);
  };
  const onMessageReceived = (msg) => {
    // const notification = JSON.parse(msg.body);
    // const active = JSON.parse(sessionStorage.getItem("recoil-persist"))
    //   .chatActiveContact;

    // if (active.id === notification.senderId) {
    //   findChatMessage(notification.id).then((message) => {
    //     const newMessages = JSON.parse(sessionStorage.getItem("recoil-persist"))
    //       .chatMessages;
    //     newMessages.push(message);
    //     setMessages(newMessages);
    //   });
    // } else {
    //   message.info("Received a new message from " + notification.senderName);
    // }
    // loadContacts();
  };
  const sendMessage = () => {
    if (messageText.value.trim() !== "") {
      const message = {
        senderId: userId.value,
        recipientId: senderId.value,
        senderName: "Richa",
        recipientName: "Ritik",
        content: messageText.value,
      };
      stompClient.send("/app/chat", {}, JSON.stringify(message));

      const newMessages = [...messages.value];
      newMessages.push(message);
      messages.value = newMessages;

    }
  };

</script>

<script>
    export default {
        name: "ChatView",
    }
</script>