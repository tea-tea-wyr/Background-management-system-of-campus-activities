<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--组织数据-->
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-input
            v-model="orgName"
            placeholder="请输入组织名称"
            clearable
            size="small"
            prefix-icon="el-icon-search"
            style="margin-bottom: 20px"
          />
        </div>
        <div class="head-container">
          <el-tree
            :data="orgOptions"
            :props="defaultProps"
            :expand-on-click-node="false"
            :filter-node-method="filterNode"
            ref="tree"
            default-expand-all
            @node-click="handleNodeClick"
          />
        </div>
      </el-col>
      <!--活动数据-->
      <el-col :span="20" :xs="24">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名称" prop="aname">
        <el-input
          v-model="queryParams.aname"
          placeholder="请输入名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="主题" prop="asubject">
        <el-input
          v-model="queryParams.asubject"
          placeholder="请输入主题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="标签类型" prop="atype">
        <el-select
          v-model="queryParams.atype"
          placeholder="请选择标签类型"
          clearable
          size="small">
<!--          <el-option-->
<!--            label="请选择字典生成"-->
<!--            value="" />-->

          <el-option
            v-for="dict in labelOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />

        </el-select>
      </el-form-item>
<!--      <el-form-item label="开始日期" prop="astart">-->
<!--        <el-date-picker clearable size="small"-->
<!--                        v-model="queryParams.astart"-->
<!--                        type="daterange"-->
<!--                        value-format="yyyy-MM-dd"-->
<!--                        placeholder="选择开始日期">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="结束日期" prop="aend">-->
<!--        <el-date-picker clearable size="small"-->
<!--                        v-model="queryParams.aend"-->
<!--                        type="daterange"-->
<!--                        value-format="yyyy-MM-dd"-->
<!--                        placeholder="选择结束日期">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="开始时间" prop="astahour">-->
<!--        <el-date-picker clearable size="small"-->
<!--                        v-model="queryParams.astahour"-->
<!--                        type="date"-->
<!--                        value-format="yy:MM:dd"-->
<!--                        placeholder="选择开始时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="结束时间" prop="aendhour">-->
<!--        <el-date-picker clearable size="small"-->
<!--                        v-model="queryParams.aendhour"-->
<!--                        type="date"-->
<!--                        value-format="yy:MM:dd"-->
<!--                        placeholder="选择结束时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
      <el-form-item label="地点" prop="alocation">
        <el-input
          v-model="queryParams.alocation"
          placeholder="请输入地点"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="对象" prop="aobject">
        <el-input
          v-model="queryParams.aobject"
          placeholder="请输入对象"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="人数" prop="anum">-->
<!--        <el-input-->
<!--          v-model="queryParams.anum"-->
<!--          placeholder="请输入人数"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="分数类型" prop="agradetype">-->
<!--        <el-select v-model="queryParams.agradetype" placeholder="请选择分数类型" clearable size="small">-->
<!--          <el-option label="请选择字典生成" value="" />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
      <el-form-item label="总时间" prop="ahour">
        <el-input
          v-model="queryParams.ahour"
          placeholder="请输入总时间"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分数" prop="agrade">
        <el-input
          v-model="queryParams.agrade"
          placeholder="请输入分数"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:act:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:act:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:act:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:act:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="actList" @selection-change="handleSelectionChange" border="border" >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="活动号" align="center" prop="actId" />
      <el-table-column label="所属组织方" align="center" prop="org.orgName" width="180px"/>
      <el-table-column label="名称" align="center" prop="aname" width="180px"/>
      <el-table-column label="主题" align="center" prop="asubject" />
      <el-table-column label="标签类型" align="center" prop="atype" width="180px">
        <template slot-scope="scope">
          <div>{{typeArr[scope.row.atype]}}</div>
        </template>

      </el-table-column>
      <el-table-column label="开始日期" align="center" prop="astart" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.astart) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束日期" align="center" prop="aend" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.aend) }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="开始时间" align="center" prop="astahour" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.astahour) }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="结束时间" align="center" prop="aendhour" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.aendhour) }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="地点" align="center" prop="alocation" width="180px"/>
      <el-table-column label="对象" align="center" prop="aobject" width="180px"/>
      <el-table-column label="人数" align="center" prop="anum" />
      <el-table-column label="分数类型" align="center" prop="agradetype">
      <template slot-scope="scope">
        <div>{{atypeArr[scope.row.agradetype]}}</div>
      </template>
      </el-table-column>
      <el-table-column label="总时间" align="center" prop="ahour" />
      <el-table-column label="分数" align="center" prop="agrade" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
        width="230"

      >
        <template slot-scope="scope">
<!--          <el-row :gutter="10" class="mb8">-->
<!--            <el-col :offset="1.5">-->
          <el-button
            size="small"
            type="primary"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:act:edit']"
          >修改</el-button>
<!--            </el-col>-->
<!--            <el-col :span="1.5">-->
          <el-button
            size="small"
            type="danger"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:act:remove']"
          >删除</el-button>
<!--          </el-col>-->
<!--          </el-row>-->
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
      </el-col>
    </el-row>
    <!-- 添加或修改活动对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="aname">
          <el-input v-model="form.aname" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="主题" prop="asubject">
          <el-input v-model="form.asubject" placeholder="请输入主题" />
        </el-form-item>
        <el-form-item label="归属部门" prop="orgId">
          <treeselect v-model="form.orgId" :options="orgOptions" :show-count="true" placeholder="请选择归属部门" />
        </el-form-item>

<!--        <el-form-item label="标签类型" prop="atype">-->
<!--          <el-select v-model="form.atype" placeholder="请选择标签类型">-->
<!--            <el-option label="请选择字典生成" value="" />-->
<!--          </el-select>-->
<!--        </el-form-item>-->
        <el-form-item label="标签类型">
          <el-select v-model="form.atype" placeholder="请选择标签类型">
            <el-option
              v-for="dict in labelOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="开始日期" prop="astart">
          <el-date-picker clearable size="small"
                          v-model="form.astart"
                          type="date"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="选择开始日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束日期" prop="aend">
          <el-date-picker clearable size="small"
                          v-model="form.aend"
                          type="date"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="选择结束日期">
          </el-date-picker>
        </el-form-item>
<!--        <el-form-item label="开始时间" prop="astahour">-->
<!--          <el-date-picker clearable size="small"-->
<!--                          v-model="form.astahour"-->
<!--                          type="date"-->
<!--                          value-format="yyyy-MM-dd"-->
<!--                          placeholder="选择开始时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="结束时间" prop="aendhour">-->
<!--          <el-date-picker clearable size="small"-->
<!--                          v-model="form.aendhour"-->
<!--                          type="date"-->
<!--                          value-format="yyyy-MM-dd"-->
<!--                          placeholder="选择结束时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
        <el-form-item label="地点" prop="alocation">
          <el-input v-model="form.alocation" placeholder="请输入地点" />
        </el-form-item>
        <el-form-item label="对象" prop="aobject">
          <el-input v-model="form.aobject" placeholder="请输入对象" />
        </el-form-item>
        <el-form-item label="人数" prop="anum">
          <el-input v-model="form.anum" placeholder="请输入人数" />
        </el-form-item>
        <el-form-item label="分数类型" prop="agradetype">
<!--          <el-select v-model="form.agradetype" placeholder="请选择分数类型">-->
<!--            <el-option label="请选择字典生成" value="" />-->
<!--          </el-select>-->

          <el-select v-model="form.agradetype" placeholder="请选择分数类型">
            <el-option
              v-for="dict in gradetypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>

        </el-form-item>
        <el-form-item label="总时间" prop="ahour">
          <el-input v-model="form.ahour" placeholder="请输入总时间(按小时计算)" />
        </el-form-item>
        <el-form-item label="分数" prop="agrade">
          <el-input v-model="form.agrade" placeholder="请输入分数" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { listAct, getAct, delAct, addAct, updateAct, exportAct } from "@/api/system/act";
  import { getToken } from "@/utils/auth";
  import { treeselect } from "@/api/system/org";
  import Treeselect from "@riophae/vue-treeselect";
  import "@riophae/vue-treeselect/dist/vue-treeselect.css";


  export default {
    name: "Act",
    components: {Treeselect
    },
    data() {

      return {
        typeArr:{
          0:"党建类",
          1:"志愿类",
          2:"学科竞赛类",
          3:"科技创新类",
        },
        atypeArr:{
          0:"分数",
          1:"时长",
        },


        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 活动表格数据
        actList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 状态数据字典
        statusOptions: [],
        // 标签状态字典
        labelOptions: [],
        // 分数类型字典
        gradetypeOptions:[],
        // 部门树选项
        orgOptions: undefined,
        // 部门名称
        orgName: undefined,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          aname: null,
          asubject: null,
          atype: null,
          astart: null,
          aend: null,
          astahour: null,
          aendhour: null,
          alocation: null,
          aobject: null,
          anum: null,
          agradetype: null,
          ahour: null,
          agrade: null,
          orgId:null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          aname: [
            { required: true, message: "名称不能为空", trigger: "blur" }
          ],
          asubject: [
            { required: true, message: "主题不能为空", trigger: "blur" }
          ],
          // atype: [
          //   { required: true, message: "标签类型不能为空", trigger: "change" }
          // ],
          alocation: [
            { required: true, message: "地点不能为空", trigger: "blur" }
          ],
        }
      };
    },
    watch: {
      // 根据名称筛选部门树
      orgName(val) {
        this.$refs.tree.filter(val);
      }
    },
    created() {
      this.getList();
      console.log(this.actList);
      this.getTreeselect();
      this.getDicts("sys_normal_disable").then(response => {
        this.statusOptions = response.data;
      });
      this.getDicts("sys_act_label").then(response => {
        this.labelOptions = response.data;
      });
      this.getDicts("sys_user_grade").then(response => {
        this.gradetypeOptions = response.data;
      });
      this.getConfigKey("sys.user.initPassword").then(response => {
        this.initPassword = response.msg;
      });
    },
    methods: {
      /** 查询活动列表 */
      getList() {
        this.loading = true;
        listAct(this.queryParams, this.dateRange).then(response => {
          this.actList = response.rows;
          this.total = response.total;
          this.loading = false;
          console.log(this.actList);
        });
      },
      /** 查询部门下拉树结构 */
      getTreeselect() {
        treeselect().then(response => {
          this.orgOptions = response.data;
        });
      },
      // 筛选节点
      filterNode(value, data) {
        if (!value) return true;
        return data.label.indexOf(value) !== -1;
      },
      // 节点单击事件
      handleNodeClick(data) {
        this.queryParams.orgId = data.id;
        this.getList();
      },

      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          actId: null,
          orgId: null,
          aname: null,
          asubject: null,
          atype: null,
          astart: null,
          aend: null,
          astahour: null,
          aendhour: null,
          alocation: null,
          aobject: null,
          anum: null,
          agradetype: null,
          ahour: null,
          agrade: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.actId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加活动";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const actId = row.actId || this.ids

        getAct(actId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改活动";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.actId != null) {
              updateAct(this.form).then(response => {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addAct(this.form).then(response => {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const aids = row.actId || this.ids;
        this.$confirm('是否确认删除活动编号为"' + aids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delAct(aids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有活动数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportAct(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
      }
    }
  };
</script>
