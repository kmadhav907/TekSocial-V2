import { Client, messageCallbackType } from '@stomp/stompjs'

export class WebsocketService {
webSocketUrl = 'ws://localhost:8083/ws'
client
onConnectCb
onDisconnectCb
onErrorCb
_isConnected = false

static instance

   constructor() {
    console.log(`${process.env.NODE_ENV === 'development' ? 'DEV' : 'PROD'} mode`)
    this.client = new Client({
      brokerURL: this.webSocketUrl,
      debug: function (str) {
        console.log('WS debug: ', str)
      },
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
    })

    this.client.onConnect = () => {
      this._isConnected = true
      this.onConnectCb && this.onConnectCb()
    }

    this.client.onDisconnect = () => {
      this._isConnected = false
      this.onDisconnectCb && this.onDisconnectCb()
    }

    this.client.onStompError = (frame) => {
      console.error('WS: Broker reported error: ' + frame.headers['message'])
      console.error('WS: Additional details: ' + frame.body)
      this.onErrorCb && this.onErrorCb()
    }
  }

  static getInstance() {
    if (!WebsocketService.instance) {
      return new WebsocketService()
    }
    return WebsocketService.instance
  }

  get isConnected() {
    return this._isConnected
  }

  connect(onConnectCb, onDisconnectCb, onErrorCb)  {
    this.onConnectCb = onConnectCb
    this.onDisconnectCb = onDisconnectCb
    this.onErrorCb = onErrorCb

    this.client.activate()
  }

  disconnect() {
    this.client.deactivate()
  }

  subscribe(destination, cb) {
    this.client.subscribe(destination, cb)
  }

  sendMessage(destination, body) {
    this.client.publish({ destination, body })
  }
}
