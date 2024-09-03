import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter as Router } from 'react-router-dom';
{/* <React.StrictMode>
</React.StrictMode> */}

const rootElement = document.getElementById('root');
const root = ReactDOM.createRoot(rootElement); // createRoot 사용

root.render(

    <App />
  
);
{/* <App /> */}


// ReactDOM.render(
//   <React.StrictMode>
//     <App />
//   </React.StrictMode>,
//   document.getElementById('root')
// );
