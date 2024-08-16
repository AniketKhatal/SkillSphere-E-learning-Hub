import React from 'react'

function About() {
  
  return (
    <> 
    <div>About Us  <br />
      <section id="team" class="team">

<h1 class="heading"><br/> Our Team</h1>

<div class="row">

<div class="card">
  <div class="image">
    {<img src={require("../about/ShubhamRokade.jpeg")}/> }
  </div>
  <div class="info">
    <h4> </h4>
    <h4>Shubham Rokade</h4>
   
  </div>
</div>

<div class="card">
  <div class="image">
    {<img  src={require("../about/AniketKhatal.jpeg")}/> }
  </div>
  <div class="info">
    <h4> </h4>
    <h4>Aniket Khatal</h4>
    
  </div>
</div>

<div class="card">
  <div class="image">
    <img src={require("../about/Piyush.jpeg")} alt=""/>
  </div>
  <div class="info">
    <h4> </h4>
    <h4>Piyush Chaudhary</h4>
    
  </div>
</div>

<div class="card">
  <div class="image">
    <img src={require("../about/Anshul.jpeg")} alt=""/>
  </div>
  <div class="info">
    <h4> </h4>
    <h4>Anshul Malviya</h4>
    
  </div>
</div>

<div class="card">
  <div class="image">
    {/* <img src={require("../about/Ashlekh[1].jpg")} alt=""/> */}
  </div>
  <div class="info">
    <h4> </h4>
    <h4>Full Stack-Web Developer</h4>
    
  </div>
</div>

<div class="card">
  <div class="image">
    {/* <img src={require("../about/Ashlekh[1].jpg")} alt=""/> */}
  </div>
  <div class="info">
    <h4> </h4>
    <h4>Full Stack-Web Developer</h4>
    
  </div>
</div>


{/* <div class="card">
  <div class="image">
    <img src={require("../about/recent.jpg")} alt=""/>
  </div>
  <div class="info">
    <h4> </h4>
    <h4>Full stack-Web Developer</h4>
    
  </div>
</div> */}
{/* <div class="card">
  <div class="image">
    <img src={require("../about/sandeep.jpeg")} alt=""/>
  </div>
  <div class="info">
    <h4></h4>
    <h4></h4>
    
  </div>
</div> */}
</div>
</section>

    </div>
    </>
  )
}

export default About