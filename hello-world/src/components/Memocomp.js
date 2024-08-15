import React from 'react'

function Memocomp({name}) {
    console.log("Rendering Memo Component")
  return (
    <div>
      {name}
    </div>
  )
}

export default React.memo(Memocomp)
