import { useState } from "react";
import Button from "../components/Button";
import RecipeContainer from "../components/RecipeContainer";
import styles from "./FindRecipePage.module.css";

function IngredientsBlock({ onSearch }) {
  return (
    <div className={styles.ingrContainer}>
      <h1>My ingredients</h1>
      <hr />
      <Button paddingBtn="20px 14%" borderRadiusBtn="50px" onClick={onSearch}>
        Search
      </Button>
    </div>
  );
}

function RecipesBlock({ recipesList }) {
  return (
    <>
      <h1>Recipes</h1>
      <div>
        <RecipeContainer recipe={recipesList} />
      </div>
    </>
  );
}

const FindRecipePage = () => {
  const [ingredients, setIngredients] = useState([]);
  const [showRecipes, setShowRecipes] = useState(false);
  const [recipes, setRecipes] = useState(null);
  const [error, setError] = useState("");
  const [isLoading, setIsLoading] = useState(false);

  const hendleSearch = () => {
    setIsLoading(true);
    setError("");
    fetch("http://localhost:8080/api/recipe/2")
      .then((res) => {
        if (!res.ok) {
          throw new Error(res.status);
        }
        return res.json();
      })
      .then((json) => {
        setRecipes(json);
        setShowRecipes(true);
      })
      .catch((err) => setError(err.message))
      .finally(() => setIsLoading(false));
  };

  if (isLoading) {
    return (
      <div className="loader-container">
        <div className="loader"></div>
      </div>
    );
  }

  if (error) {
    return <h1 className={styles.message}>Error: {error}</h1>;
  }

  return (
    <div>
      {showRecipes ? (
        <RecipesBlock recipesList={recipes} />
      ) : (
        <IngredientsBlock onSearch={hendleSearch} />
      )}
    </div>
  );
};

export default FindRecipePage;
