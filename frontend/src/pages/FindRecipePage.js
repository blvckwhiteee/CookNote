import { useEffect, useRef, useState } from "react";
import IngredientsBlock from "../components/IngredientsBlock";
import RecipesBlock from "../components/RecipesBlock";
import { useRecipeContext } from "../store/Context";
import API from "../services/api";

const asyncDecorator = (
  asyncFunc,
  timerRef,
  setIsLoading,
  setError,
  dependence = null
) => {
  return async function () {
    timerRef.current = setTimeout(() => setIsLoading(true), 2000);
    setError("");
    try {
      await asyncFunc(dependence);
    } catch (err) {
      setError(err.message);
    } finally {
      clearTimeout(timerRef.current);
      setIsLoading(false);
    }
  };
};

const FindRecipePage = () => {
  const { selectedIngr, setSelectedIngr, setRecipes, showRecipes, setShowRecipes } =
    useRecipeContext();
  const [allIngredients, setAllIngredients] = useState([]);
  const [error, setError] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const loaderTimeoutRef = useRef(null);

  const searchIngrs = async () => {
    const data = await API.ingredients();
    setAllIngredients(data);
  };

  useEffect(() => {
    if (!allIngredients.length) {
      asyncDecorator(searchIngrs, loaderTimeoutRef, setIsLoading, setError)();
    }
  }, []);

  const searchRecipes = async (ingrs) => {
    const queryParams = ingrs
      .map((ingr) => `ingredientNames=${encodeURIComponent(ingr.name)}`)
      .join("&");
    const data = await API.ingredients(`search?${queryParams}`);
    setRecipes(data);
    setShowRecipes(true);
  };

  const handleSearch = asyncDecorator(
    searchRecipes,
    loaderTimeoutRef,
    setIsLoading,
    setError,
    selectedIngr
  );

  if (isLoading) {
    return (
      <div className="loader-container">
        <div className="loader"></div>
      </div>
    );
  }

  if (error) {
    return <h1 className="message">Error: {error}</h1>;
  }

  return (
    <div>
      {showRecipes ? (
        <RecipesBlock />
      ) : (
        <IngredientsBlock
          ingredients={allIngredients}
          onSearch={handleSearch}
          selectedIngr={selectedIngr}
          setSelectedIngr={setSelectedIngr}
        />
      )}
    </div>
  );
};

export default FindRecipePage;
