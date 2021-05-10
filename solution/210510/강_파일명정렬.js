function solution(files) {
  files.sort((a, b) => {
    let aNum = a.split(".")[0].replace(/[^0-9]/g, "");
    let aNumIndex = a.indexOf(aNum);
    let bNum = b.split(".")[0].replace(/[^0-9]/g, "");
    let bNumIndex = b.indexOf(bNum);

    // head 비교
    if (a.slice(0, aNumIndex).toLowerCase() > b.slice(0, bNumIndex).toLowerCase()) {
      return 1;
    } else if (a.slice(0, aNumIndex).toLowerCase() < b.slice(0, bNumIndex).toLowerCase()) {
      return -1;
    } else {
      if (Number(aNum) >= Number(bNum)) {
        return 1;
      } else {
        return -1;
      }
    }
  });
  return files;
}
console.log(solution(["img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"]));
