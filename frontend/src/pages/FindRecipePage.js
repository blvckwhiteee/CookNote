import { useEffect, useState } from "react";
import IngredientsBlock from "../components/IngredientsBlock";
import RecipesBlock from "../components/RecipesBlock";
import API from "../services/api";

const FindRecipePage = () => {
  const [allIngredients, setAllIngredients] = useState([]);
  const [selectedIngr, setSelectedIngr] = useState([]);
  const [showRecipes, setShowRecipes] = useState(false);
  const [recipes, setRecipes] = useState(null);
  const [error, setError] = useState("");
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    setIsLoading(true);
    API.ingredients()
      .then((json) => {
        setAllIngredients(json);
      })
      .catch((err) => setError(err.message))
      .finally(() => setIsLoading(false));
  }, []);

  const handleSearch = async () => {
    if (selectedIngr.length === 0) return;
    setIsLoading(true);
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
        <RecipesBlock recipesList={recipes} />
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
