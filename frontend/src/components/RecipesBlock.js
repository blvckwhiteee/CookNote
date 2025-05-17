import { useState, useMemo } from "react";
import { useRecipeContext } from "../store/Context";
import RecipeContainer from "../components/RecipeContainer";
import { GreenButton } from "./Button";
import Sorter from "./Sorter";
import styles from "./RecipesBlock.module.css";

const RecipesBlock = () => {
  const { recipes, setShowRecipes } = useRecipeContext();
  const [sortMethod, setSortMethod] = useState("");

  const sortedByTime = useMemo(() => {
    const sortedList = [...recipes];
    return sortedList.sort((a, b) => a.timeToCook - b.timeToCook);
  }, [recipes]);

  const sortedByMathces = useMemo(() => {
    const sortedList = [...recipes];
    return sortedList.sort((a, b) => b.matchedIngredients - a.matchedIngredients);
  }, [recipes]);

  const displayList = useMemo(() => {
    if (sortMethod === "ByTime") return sortedByTime;
    if (sortMethod === "ByMatches") return sortedByMathces;
    return recipes;
  }, [recipes, sortedByTime, sortedByMathces, sortMethod]);

  const handleBack = () => setShowRecipes(false);
  return (
    <>
      <div className={styles.header}>
        <h1>Recipes</h1>
        <div className={styles.rightBox}>
          <Sorter sortMethod={sortMethod} setSortMethod={setSortMethod} />
          <GreenButton paddingBtn="8px 20px" borderRadiusBtn="50px" onClick={handleBack}>
            Back
          </GreenButton>
        </div>
      </div>
      <div>
        {displayList.map((recipe) => (
          <RecipeContainer key={recipe.id} recipe={recipe} />
        ))}
      </div>
    </>
  );
};

export default RecipesBlock;
