import { createContext, useContext, useEffect, useState } from "react";
import { useLocation } from "react-router-dom";

const RecipeContext = createContext();

const RecipeProvider = ({ children }) => {
  const [selectedIngr, setSelectedIngr] = useState([]);
  const [recipes, setRecipes] = useState(null);
  const [showRecipes, setShowRecipes] = useState(false);

  const path = useLocation();

  useEffect(() => {
    if (!path.pathname.startsWith("/find-recipe")) {
      setSelectedIngr([]);
      setRecipes(null);
      setShowRecipes(false);
    }
  }, [path.pathname]);

  return (
    <RecipeContext.Provider
      value={{
        selectedIngr,
        setSelectedIngr,
        recipes,
        setRecipes,
        showRecipes,
        setShowRecipes,
      }}
    >
      {children}
    </RecipeContext.Provider>
  );
};

const useRecipeContext = () => useContext(RecipeContext);

export { RecipeProvider, useRecipeContext };
