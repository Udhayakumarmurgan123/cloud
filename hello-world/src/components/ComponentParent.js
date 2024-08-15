import React from 'react'

function ComponentParent() {
  return (
    <div>
      <h1>Hii Good Morning</h1>
      <ComponentChild name = "Udhay" age="19" sec="D Section" FavSubject="React" />
    </div>
  )
}
function ComponentChild(props)
{
   return(
    <div>
        <h2>Hii I'm child component I'm {props.name}  and my age is {props.age} , the section is {props.sec}, and my fav sub { props.FavSubject }....... </h2>
    </div>
   )
}

export default ComponentParent
