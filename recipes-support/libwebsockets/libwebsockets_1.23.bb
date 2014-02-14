DESCRIPTION = "libwebsockets" 
DEPENDS = "openssl"
LICENSE = "LGPLv2.1"
PR = "r0" 

SRCREV = "9bdcf18e740c4ae46cfe4034b326fbdef2620a18"
SRC_URI = "git://git.libwebsockets.org/libwebsockets;protocol=git;branch=master \
           file://libwebsockets_1.23_memory_leak_context_destroy.patch \
           file://libwebsockets_1.23_sigusr2_werror.patch"

#tag=v1.23-chrome32-firefox24

LIC_FILES_CHKSUM = "file://LICENSE;md5=041a1dec49ec8a22e7f101350fd19550"


inherit pkgconfig cmake


S = "${WORKDIR}/git"

# Do an out-of-tree build
OECMAKE_SOURCEPATH = "${S}"
OECMAKE_BUILDPATH = "${WORKDIR}/build"


EXTRA_OECMAKE = '-DCMAKE_INSTALL_PREFIX:PATH="${prefix}" \
				 -DCMAKE_FIND_ROOT_PATH="${STAGING_DIR_HOST}" \
				 -DCMAKE_FIND_ROOT_PATH_MODE_LIBRARY=ONLY \
				 -DCMAKE_FIND_ROOT_PATH_MODE_INCLUDE=ONLY \
				 -DCMAKE_SYSTEM_NAME=Linux \
				 -DCMAKE_C_FLAGS="${BUILD_CFLAGS}" \
				  -DCMAKE_C_COMPILER="${TOOLCHAIN_PATH}/${TARGET_PREFIX}${BUILD_CC}" \
				  -DCMAKE_CXX_FLAGS="${BUILD_CXXFLAGS}" \
				  -DCMAKE_CXX_COMPILER="${TOOLCHAIN_PATH}/${TARGET_PREFIX}${BUILD_CXX}"\
				 -DWITHOUT_EXTENSIONS=1 \
				 -DWITH_SSL=0 \
				  -DWITHOUT_TESTAPPS=1'

EXTRA_OEMAKE = "-C ${OECMAKE_BUILDPATH}"
