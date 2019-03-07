LOCAL_PATH:= $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := $(call all-java-files-under, src/main/java)
LOCAL_MANIFEST_FILE :=src/main/AndroidManifest.xml
LOCAL_RESOURCE_DIR :=${LOCAL_PATH}/src/main/res

LOCAL_PACKAGE_NAME := testapp

include $(BUILD_PACKAGE)
