import IngrSearch from "../components/IngrSearch";
import Button from "../components/Button";
import styles from "./IngredientsBlock.module.css";

const IngredientsBlock = ({ onSearch, ingredients, selectedIngr, setSelectedIngr }) => {
  return (
    <div className={styles.ingrContainer}>
      <h1>My ingredients</h1>
      <hr />
      <IngrSearch
        ingredients={ingredients}
        selectedIngr={selectedIngr}
        setSelectedIngr={setSelectedIngr}
      />
      {selectedIngr.length === 0 && <p>Add at least one ingredient</p>}
      <div className={styles.ingrBtnSearch}>
        <Button paddingBtn="20px 10%" borderRadiusBtn="50px" onClick={onSearch}>
          Search
        </Button>
      </div>
    </div>
  );
};

export default IngredientsBlock;
