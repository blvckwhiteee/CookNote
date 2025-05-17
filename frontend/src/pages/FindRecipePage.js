import { useEffect, useRef, useState } from "react";
import IngredientsBlock from "../components/IngredientsBlock";
import RecipesBlock from "../components/RecipesBlock";
import { useRecipeContext } from "../store/Context";
import API from "../services/api";

const FindRecipePage = () => {
  const { selectedIngr, setSelectedIngr, setRecipes, showRecipes, setShowRecipes } =
    useRecipeContext();
  const [allIngredients, setAllIngredients] = useState([]);
  const [error, setError] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const loaderTimeoutRef = useRef(null);

  useEffect(() => {
    if (!allIngredients.length) {
      loaderTimeoutRef.current = setTimeout(() => setIsLoading(true), 300);
      API.ingredients()
        .then((json) => {
          setAllIngredients(json);
        })
        .catch((err) => setError(err.message))
        .finally(() => {
          clearTimeout(loaderTimeoutRef.current);
          setIsLoading(false);
        });
    }
  }, []);

  const handleSearch = async () => {
    if (selectedIngr.length === 0) return;
    loaderTimeoutRef.current = setTimeout(() => setIsLoading(true), 300);
    setError("");
    const queryParams = selectedIngr
      .map((ingr) => `ingredientNames=${encodeURIComponent(ingr.name)}`)
      .join("&");
    try {
      const data = await API.ingredients(`search?${queryParams}`);
      setRecipes(data);
      setShowRecipes(true);
    } catch (err) {
      setError(err.message);
    } finally {
      clearTimeout(loaderTimeoutRef.current);
      setIsLoading(false);
    }
  };

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
