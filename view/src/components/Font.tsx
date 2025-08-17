import React from "react";

const Font: React.FC<{
  children: React.ReactNode;
  size?: string;
  weight?: string;
  color?: string;
  className?: string;
}> = ({ children, size = "1rem",color="black", weight = "normal", className = "" }) => (
  <span
    className={`custom-font ${className}`.trim()}
    style={{ color, fontSize: size, fontWeight: weight }}
  >
    {children}
  </span>
);

export default Font;
