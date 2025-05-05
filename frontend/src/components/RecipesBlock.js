import RecipeContainer from "../components/RecipeContainer";

const RecipesBlock = ({ recipesList }) => {
  return (
    <>
      <h1>Recipes</h1>
      <div>
        {recipesList.map((recipe) => (
          <RecipeContainer key={recipe.id} recipe={recipe} />
        ))}
      </div>
    </>
  );
};

export default RecipesBlock;
