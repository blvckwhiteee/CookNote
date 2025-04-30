import { Link } from "react-router-dom";
import styles from "./ButtonLink.module.css";

const ButtonLink = ({ to, children, paddingBtn, borderRadiusBtn }) => {
  return (
    <Link to={to}>
      <button
        className={styles.button}
        style={{ padding: paddingBtn, borderRadius: borderRadiusBtn }}
      >
        {children}
      </button>
    </Link>
  );
};

export default ButtonLink;
