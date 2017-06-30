TARGETS = console-setup alsa-utils mountkernfs.sh ufw plymouth-log pppd-dns hostname.sh x11-common dns-clean apparmor lm-sensors screen-cleanup udev resolvconf mountdevsubfs.sh procps brltty hwclock.sh checkroot.sh networking urandom rpcbind checkfs.sh bootmisc.sh mountnfs.sh kmod mountall.sh checkroot-bootclean.sh mountnfs-bootclean.sh mountall-bootclean.sh
INTERACTIVE = console-setup udev checkroot.sh checkfs.sh
udev: mountkernfs.sh
resolvconf: dns-clean
mountdevsubfs.sh: mountkernfs.sh udev
procps: mountkernfs.sh udev
brltty: mountkernfs.sh udev
hwclock.sh: mountdevsubfs.sh
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh
networking: resolvconf mountkernfs.sh urandom procps dns-clean
urandom: hwclock.sh
rpcbind: networking
checkfs.sh: checkroot.sh
bootmisc.sh: udev checkroot-bootclean.sh mountnfs-bootclean.sh mountall-bootclean.sh
mountnfs.sh: networking rpcbind
kmod: checkroot.sh
mountall.sh: checkfs.sh checkroot-bootclean.sh
checkroot-bootclean.sh: checkroot.sh
mountnfs-bootclean.sh: mountnfs.sh
mountall-bootclean.sh: mountall.sh
