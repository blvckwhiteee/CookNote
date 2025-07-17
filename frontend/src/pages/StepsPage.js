import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { MdOutlineArrowForwardIos, MdOutlineArrowBackIos } from "react-icons/md";
import { GreenButton } from "../components/Button";
import CountDownTimer from "../components/CountDownTimer";
import Loader from "../components/Loader";
import styles from "./StepsPage.module.css";
import API from "../services/api";

const StepsPage = () => {
  const { recipeId } = useParams();
  const [recipeSteps, setRecipeSteps] = useState(null);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState("");
  const [currentStep, setCurrentStep] = useState(0);
  const [durationTimer, setDurationTimer] = useState(0);

  useEffect(() => {
    setIsLoading(true);
    API.recipe(`${recipeId}/steps`)
      .then((json) => setRecipeSteps(json))
      .catch((err) => setError(err.message))
      .finally(() => {
        setIsLoading(false);
      });
  }, [recipeId]);

  useEffect(() => {
    if (!recipeSteps) return;
    setDurationTimer(recipeSteps[currentStep].stepTime * 1000);
  }, [currentStep, recipeSteps]);

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
    return <Loader isLoading={isLoading} />;
  }

  if (error) {
    return <h1 className="message">Error: {error}</h1>;
  }

  return (
    <>
      {recipeSteps && (
        <div className={styles.container}>
          <div className={styles.timer}>
            <CountDownTimer duration={durationTimer} />
          </div>

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
