import { useEffect, useRef, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { MdOutlineArrowForwardIos, MdOutlineArrowBackIos } from "react-icons/md";
import { Button, GreenButton } from "../components/Button";
import styles from "./StepsPage.module.css";
import API from "../services/api";

const StepsPage = () => {
  const { recipeId } = useParams();
  const [recipeSteps, setRecipeSteps] = useState(null);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState("");
  const loaderTimeoutRef = useRef(null);
  const [currentStep, setCurrentStep] = useState(0);

  useEffect(() => {
    loaderTimeoutRef.current = setTimeout(() => setIsLoading(true), 2000);
    API.recipe(`${recipeId}/steps`)
      .then((json) => setRecipeSteps(json))
      .catch((err) => setError(err.message))
      .finally(() => {
        clearTimeout(loaderTimeoutRef.current);
        setIsLoading(false);
      });
  }, [recipeId]);

  const onNextStep = () => {
    let step = currentStep;
    step = step >= recipeSteps.length ? recipeSteps.length - 1 : step + 1;
    setCurrentStep(step);
  };

  const onPrevStep = () => {
    let step = currentStep;
    step = step <= 0 ? 0 : step - 1;
    setCurrentStep(step);
  };

  const nextBtn = () => {
    const step = currentStep;
    if (step < recipeSteps.length - 1) {
      return (
        <div className={styles.iconBtn}>
          <GreenButton paddingBtn="10px" borderRadiusBtn="50%" onClick={onNextStep}>
            <MdOutlineArrowForwardIos />
          </GreenButton>
        </div>
      );
    }
  };

  const prevBtn = () => {
    const step = currentStep;
    if (step !== 0 && step <= recipeSteps.length) {
      return (
        <div className={styles.iconBtn}>
          <GreenButton paddingBtn="10px" borderRadiusBtn="50%" onClick={onPrevStep}>
            <MdOutlineArrowBackIos />
          </GreenButton>
        </div>
      );
    }
  };

  const backBtn = () => {
    const step = currentStep;
    if (step === 0) {
      return (
        <Link to=".." relative="path">
          <GreenButton paddingBtn="8px 20px" borderRadiusBtn="50px">
            Back
          </GreenButton>
        </Link>
      );
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
    <>
      {recipeSteps && (
        <div className={styles.container}>
          <Button paddingBtn="12px 36px" borderRadiusBtn="50px">
            Start timer
          </Button>

          <h1>Step {recipeSteps[currentStep].stepNumber}</h1>
          <div className={styles.description}>
            <p>{recipeSteps[currentStep].description}</p>
          </div>
          <div className={styles.footerButton}>
            {backBtn()}
            {prevBtn()}
            {nextBtn()}
          </div>
        </div>
      )}
    </>
  );
};

export default StepsPage;
