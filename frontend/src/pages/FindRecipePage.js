import { useEffect, useState } from "react";
import Button from "../components/Button";
import RecipeContainer from "../components/RecipeContainer";
import IngrSearch from "../components/IngrSearch";
import styles from "./FindRecipePage.module.css";

function IngredientsBlock({ onSearch, ingredients }) {
  return (
    <div className={styles.ingrContainer}>
      <h1>My ingredients</h1>
      <hr />
      <IngrSearch ingredients={ingredients} />
      <div className={styles.ingrBtnSearch}>
        <Button paddingBtn="20px 10%" borderRadiusBtn="50px" onClick={onSearch}>
          Search
        </Button>
      </div>
    </div>
  );
}

function RecipesBlock({ recipesList }) {
  return (
    <>
      <h1>Recipes</h1>
      <div>
        {/* {recipesList.map(recipe => {
        <RecipeContainer key={recipe.id} recipe={recipe}/>
      })} */}
        <RecipeContainer recipe={recipesList} />
      </div>
    </>
  );
}

const FindRecipePage = () => {
  const [allIngredients, setAllIngredients] = useState([]);
  const [showRecipes, setShowRecipes] = useState(false);
  const [recipes, setRecipes] = useState(null);
  const [error, setError] = useState("");
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    setIsLoading(true);
    fetch("http://localhost:8080/api/ingredients")
      .then((res) => {
        if (!res.ok) {
          throw new Error(res.status);
        }
        return res.json();
      })
      .then((json) => {
        setAllIngredients(json);
      })
      .catch((err) => setError(err.message))
      .finally(() => setIsLoading(false));
  }, []);

  const hendleSearch = async () => {
    setIsLoading(true);
    setError("");
    try {
      const res = await fetch("http://localhost:8080/api/recipe/2");
      if (!res.ok) {
        throw new Error(res.status);
      }
      const recipes = await res.json();
      setRecipes(recipes);
      setShowRecipes(true);
    } catch (err) {
      setError(err.message);
    }
    setIsLoading(false);
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
        <IngredientsBlock ingredients={allIngredients} onSearch={hendleSearch} />
      )}
    </div>
  );
};

export default FindRecipePage;
