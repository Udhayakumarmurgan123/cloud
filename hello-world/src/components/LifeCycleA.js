import React, { Component } from 'react'
import LifeCycleB from './LifeCycleB';

class LifeCycleA extends Component {
    constructor(props) {
      super(props)
    
      this.state = {
         name : 'Udhay'
      }
      console.log('LifeCycleA constructor')
    }
    static getDerivedStateFromProps(state,props)
    {
        console.log('LifecycleA getDerivedStateFromProps')
        return null
    }
componentDidMount ()
{
    console.log('LifeCycleA componentDidMount')
}
    
  render() {
    console.log('LifeCycleA render')
    return
    (
        <div>
    <div>LifeCycleA</div>
    <LifeCycleB />
    </div>
    )
  }
}

export default LifeCycleA
