import React from "react";
import InstructorDashboard from "../components/instructor/InstructorDashboard";

// Main container for the instructor's dashboard after login
function InstructorScreen() {
  return (
    <div className="container-fluid">
      {/* Full-width container */}
      <div className="row flex-nowrap">
        {/* Flexbox row to create a horizontal layout */}
        <InstructorDashboard />
        {/* Including the InstructorDashboard component */}
      </div>
    </div>
  );
}

export default InstructorScreen;
