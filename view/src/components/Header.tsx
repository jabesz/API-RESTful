import React from "react";
import Font from "./Font";

const Header: React.FC<{ title: string }> = ({ title }) => (
  <header className="custom-header">
    <div className="container d-flex align-items-center justify-content-between">
      <h1 className="custom-header-title">
        <Font size="2.2rem" className="custom-font">
          {title}
        </Font>
      </h1>
    </div>
  </header>
);

export default Header;
