//package com.mericaltikardes.suffixoperations.component;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StopWatch;
//import org.springframework.web.bind.annotation.SessionAttributes;
//
//@Aspect
//@Component
//
//public class ResponseTime {
//
//    @Around(value = "execution(* com.mericaltikardes.suffixoperations.controller.SuffixController.compareToStrings(..))")
//    public Object measureTime(ProceedingJoinPoint joinPoint) throws Throwable {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//
//        Object result = joinPoint.proceed(); // İşlemi yürütme metodu
//        stopWatch.stop();
//        System.out.println("Total time: " + stopWatch.getTotalTimeNanos()+ " nanoseconds.");
//
//        return result;
//    }
//}
