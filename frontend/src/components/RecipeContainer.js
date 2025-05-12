import { Link } from "react-router-dom";
import { PiTimerBold } from "react-icons/pi";
import styles from "./RecipeContainer.module.css";

const RecipeContainer = ({ recipe }) => {
  return (
    <div className={styles.recipeContainer}>
      <Link to={`/find-recipe/${recipe.title}/${recipe.id}`}>
        <div className={styles.imgBox}>
          <img src="/imgs_delete/borsch-36745.jpg" alt={recipe.title} />
        </div>
        <div className={styles.infoRecipe}>
          <h1>{recipe.title}</h1>
          <p>
            <PiTimerBold />
            {recipe.timeToCook} sec
          </p>
        </div>
      </Link>
    </div>
  );
};

export default RecipeContainer;
