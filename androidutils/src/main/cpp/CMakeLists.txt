cmake_minimum_required(VERSION 3.10.2)

project("image_utils")

add_library(image_utils SHARED image_utils.cpp)

find_library(log-lib log)

include_directories(${CMAKE_CURRENT_LIST_DIR}/include/)

add_library(OPENCV SHARED IMPORTED)
set_target_properties(OPENCV PROPERTIES IMPORTED_LOCATION ${CMAKE_CURRENT_LIST_DIR}/../../../libs/${CMAKE_ANDROID_ARCH_ABI}/libopencv_java3.so)

target_link_libraries( # Specifies the target library.
        image_utils
        OPENCV
        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})