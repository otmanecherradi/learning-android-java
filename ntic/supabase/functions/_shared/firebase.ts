// @deno-types=https://cdn.skypack.dev/-/firebase@v8.7.0-MrU9zUCxcEMCl2U7Tuz6/dist=es2020,mode=types/index.d.ts
import firebase from 'https://cdn.skypack.dev/firebase@8.7.0/app';
import 'https://cdn.skypack.dev/firebase@8.7.0/messaging';

const firebaseConfig = {
  apiKey: 'AIzaSyAhuM4hBi-K47ftFlGK4GPGfmvC9eavdmM',
  authDomain: 'ismontic-ebbc3.firebaseapp.com',
  projectId: 'ismontic-ebbc3',
  storageBucket: 'ismontic-ebbc3.appspot.com',
  messagingSenderId: '1015462424477',
  appId: '1:1015462424477:web:f5a70a4c875cf60ada0974',
  measurementId: 'G-XYVXV05VHW',
};

const firebaseApp = firebase.initializeApp(firebaseConfig, 'example');
const messaging = firebaseApp.messaging();

export { messaging };

// fqgfTqFkRdyeravu7vYFwq:APA91bGD1Kj8-DVQSgh7cr0_1UcT5BJfPiwrdQFtxNFf7PFwGvHy7pSAF2SNbmznCnUoOrxUVWtnMeZvVA0td21o7_ul3_bLM87in9sz1_tK9sjml9-iboNW_v1IqWVY__Y_YLzvfMdQ
