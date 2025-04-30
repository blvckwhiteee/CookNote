import { Link, Outlet } from "react-router-dom";
import { FaHome } from "react-icons/fa";
import styles from "./Header.module.css";

const Header = () => {
  return (
    <>
      <div className={styles.header}>
        <Link to=".">
          <div className={styles.header}>
            <FaHome className="icon" />
            Cook Note
          </div>
        </Link>
      </div>
      <Outlet />
    </>
  );
};

export default Header;
