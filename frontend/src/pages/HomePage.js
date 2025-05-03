import Button from "../components/Button";
import { Link } from "react-router-dom";
import { FaSearch } from "react-icons/fa";
import { FaPlus } from "react-icons/fa";
import styles from "./HomePage.module.css";

const HomePage = () => {
  return (
    <div className={styles.container}>
      <h1>Welcome to the Cook Note!</h1>
      <p>This site is your helper and guide to the world of cooking!</p>
      <div className={styles.buttonContainer}>
        <Link to="find-recipe">
          <Button paddingBtn="20px 30px" borderRadiusBtn="20px">
            <FaSearch className="icon" />
            Find a recipe
          </Button>
        </Link>
        <Link to="create-recipe">
          <Button paddingBtn="20px 30px" borderRadiusBtn="20px">
            <FaPlus className="icon" />
            Create a recipe
          </Button>
        </Link>
      </div>
      <p className={styles.textCenter}>
        You can find a recipe you want or create your own!
      </p>
    </div>
  );
};

export default HomePage;
