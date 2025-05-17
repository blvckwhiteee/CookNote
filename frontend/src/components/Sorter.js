import { useState } from "react";
import { FaSortAmountDown } from "react-icons/fa";
import styles from "./Sorter.module.css";

const Sorter = ({ sortMethod, setSortMethod }) => {
  const [isOpen, setIsOpen] = useState(false);

  const toggleDropdown = () => setIsOpen(!isOpen);

  const handleSort = (method) => {
    setSortMethod(method);
    setIsOpen(false);
  };

  return (
    <div className={styles.sorter}>
      <button onClick={toggleDropdown}>
        <FaSortAmountDown />
      </button>
      {isOpen && (
        <div className={styles.dropdownContent}>
          <button onClick={() => handleSort("ByTime")}>Sort by time</button>
          <button onClick={() => handleSort("ByMatches")}>Sort by matches</button>
          <button onClick={() => handleSort("")}>Reset</button>
        </div>
      )}
    </div>
  );
};

export default Sorter;
