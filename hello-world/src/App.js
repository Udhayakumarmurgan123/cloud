import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
//import PureComp from './components/PureComp';
import ParentComp from './components/ParentComp';

class App extends Component {
  render() {     
  return ( 
    <div>
    {/* <MouseOver/> */}
   {/* <PureComp />  */}
    <ParentComp
     />

       </div>
    );
  }
}

export default App;
 