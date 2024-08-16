import React from "react";

// This component represents the dashboard for the instructor
function InstructorDashboard() {
  return (
    <div className="col-auto col-md-3 col-xl-2 px-sm-2 px-0 bg-dark">
      {/* Sidebar container with Bootstrap classes for responsive design */}
      <div className="d-flex flex-column align-items-center align-items-sm-start px-3 pt-2 text-white min-vh-100">
        {/* Container for the sidebar content with alignment and padding */}
        <a href="/" className="d-flex align-items-center pb-3 mb-md-0 me-md-auto text-white text-decoration-none">
          {/* Dashboard header link */}
          <span className="fs-5 d-none d-sm-inline">Instructor Dashboard</span>
          {/* Dashboard title, hidden on small screens */}
        </a>
        <ul className="nav nav-pills flex-column mb-sm-auto mb-0 align-items-center align-items-sm-start" id="menu">
          {/* Navigation list */}
          <li className="nav-item">
            <a href="#" className="nav-link align-middle px-0">
              {/* Home link */}
              <i className="fs-4 bi-house"></i> <span className="ms-1 d-none d-sm-inline">Home</span>
              {/* Icon and text */}
            </a>
          </li>
          <li>
            <a href="#submenu1" data-bs-toggle="collapse" className="nav-link px-0 align-middle">
              {/* Dashboard link with submenu */}
              <i className="fs-4 bi-speedometer2"></i> <span className="ms-1 d-none d-sm-inline">Dashboard</span>
              {/* Icon and text */}
            </a>
            <ul className="collapse show nav flex-column ms-1" id="submenu1" data-bs-parent="#menu">
              {/* Collapsible submenu */}
              <li className="w-100">
                <a href="#" className="nav-link px-0"> <span className="d-none d-sm-inline">Item</span> 1</a>
                {/* Submenu item 1 */}
              </li>
              <li>
                <a href="#" className="nav-link px-0"> <span className="d-none d-sm-inline">Item</span> 2</a>
                {/* Submenu item 2 */}
              </li>
            </ul>
          </li>
          <li>
            <a href="#" className="nav-link px-0 align-middle">
              {/* Another navigation link */}
              <i className="fs-4 bi-table"></i> <span className="ms-1 d-none d-sm-inline">Another Link</span>
              {/* Icon and text */}
            </a>
          </li>
        </ul>
      </div>
    </div>
  );
}

export default InstructorDashboard;
