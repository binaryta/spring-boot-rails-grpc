import React from 'react';
import ReactDOM from 'react-dom';
import { Tasks } from './Task';

window.onload = () => {
  ReactDOM.render(
    <Tasks/>,
    document.querySelector('#root')
  );
}
